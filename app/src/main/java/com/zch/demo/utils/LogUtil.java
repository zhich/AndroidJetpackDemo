package com.zch.demo.utils;

import android.content.ContentValues;
import android.text.TextUtils;
import android.util.Log;

import com.zch.demo.BuildConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * Log日志工具类
 *
 * @author zch 2017-1-13
 */
public class LogUtil {

    private static final String TAG = "LogUtil";//默认的tag
    private static final int LOG_LEVEL = Log.VERBOSE;// 日志输出级别

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private static final int VERBOSE = 1;
    private static final int DEBUG = 2;
    private static final int INFO = 3;
    private static final int WARN = 4;
    private static final int ERROR = 5;
    private static final int JSON = 6;
    private static final int XML = 7;
    private static final int JSON_INDENT = 4;

    private static boolean sIsDebug = BuildConfig.DEBUG; //是否输出日志

    private LogUtil() {
    }

    /**
     * 是否输出日志
     *
     * @param isDebug
     */
    public static void setDebug(boolean isDebug) {
        sIsDebug = isDebug;
    }

    public static void i(String tag, Object str) {
        if (sIsDebug && LOG_LEVEL <= Log.INFO) {
            log(INFO, tag, str);
        }
    }

    public static void i(Object str) {
        if (sIsDebug && LOG_LEVEL <= Log.INFO) {
            log(INFO, TAG, str);
        }
    }

    public static void v(String tag, Object str) {
        if (sIsDebug && LOG_LEVEL <= Log.VERBOSE) {
            log(VERBOSE, tag, str);
        }
    }

    public static void v(Object str) {
        if (sIsDebug && LOG_LEVEL <= Log.VERBOSE) {
            log(VERBOSE, TAG, str);
        }
    }

    public static void w(String tag, Object str) {
        if (sIsDebug && LOG_LEVEL <= Log.WARN) {
            log(WARN, tag, str);
        }
    }

    public static void w(Object str) {
        if (sIsDebug && LOG_LEVEL <= Log.WARN) {
            log(WARN, TAG, str);
        }
    }

    public static void e(String tag, Object str) {
        if (sIsDebug && LOG_LEVEL <= Log.ERROR) {
            log(ERROR, tag, str);
        }
    }

    public static void e(Object str) {
        if (sIsDebug && LOG_LEVEL <= Log.ERROR) {
            log(ERROR, TAG, str);
        }
    }

    public static void d(String tag, Object str) {
        if (sIsDebug && LOG_LEVEL <= Log.DEBUG) {
            log(DEBUG, tag, str);
        }
    }

    public static void d(Object str) {
        if (sIsDebug && LOG_LEVEL <= Log.DEBUG) {
            log(DEBUG, TAG, str);
        }
    }

    public static void json(String tag, Object str) {
        if (sIsDebug) {
            log(JSON, tag, str);
        }
    }

    public static void json(Object str) {
        if (sIsDebug) {
            log(JSON, TAG, str);
        }
    }

    public static void xml(String tag, Object str) {
        if (sIsDebug) {
            log(XML, tag, str);
        }
    }

    public static void xml(Object str) {
        if (sIsDebug) {
            log(XML, TAG, str);
        }
    }

    private static void log(int logType, String tagStr, Object objects) {
        String[] contents = wrapperContent(tagStr, objects);
        String tag = contents[0];
        String msg = contents[1];
        String headString = contents[2];
        switch (logType) {
            case VERBOSE:
            case DEBUG:
            case INFO:
            case WARN:
            case ERROR:
                printDefault(logType, tag, headString + msg);
                break;
            case JSON:
                printJson(tag, msg, headString);
                break;
            case XML:
                printXml(tag, msg, headString);
                break;
            default:
                break;
        }
    }

    private static String[] wrapperContent(String tag, Object... objects) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement targetElement = stackTrace[5];
        String className = targetElement.getClassName();
        String[] classNameInfo = className.split("\\.");
        if (classNameInfo.length > 0) {
            className = classNameInfo[classNameInfo.length - 1] + ".java";
        }
        String methodName = targetElement.getMethodName();
        int lineNumber = targetElement.getLineNumber();
        if (lineNumber < 0) {
            lineNumber = 0;
        }
        String methodNameShort = methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
        String msg = (objects == null) ? "Log with null object" : getObjectsString(objects);
        String headString = "[(" + className + ":" + lineNumber + ")#" + methodNameShort + " ] ";
        return new String[]{tag, msg, headString};
    }

    private static String getObjectsString(Object... objects) {
        if (objects.length > 1) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("\n");
            for (int i = 0; i < objects.length; i++) {
                Object object = objects[i];
                if (object == null) {
                    stringBuilder.append("param").append("[").append(i).append("]").append(" = ").append("null").append("\n");
                } else {
                    stringBuilder.append("param").append("[").append(i).append("]").append(" = ").append(object.toString()).append("\n");
                }
            }
            return stringBuilder.toString();
        } else {
            Object object = objects[0];
            return object == null ? "null" : object.toString();
        }
    }

    private static void printDefault(int type, String tag, String msg) {
        int index = 0;
        int maxLength = 4000;
        int countOfSub = msg.length() / maxLength;

        if (countOfSub > 0) {  // The log is so long
            for (int i = 0; i < countOfSub; i++) {
                String sub = msg.substring(index, index + maxLength);
                printSub(type, tag, sub);
                index += maxLength;
            }
        } else {
            printSub(type, tag, msg);
        }

    }

    private static void printSub(int type, String tag, String sub) {
        printLine(tag, true);
        switch (type) {
            case VERBOSE:
                Log.v(tag, sub);
                break;
            case DEBUG:
                Log.d(tag, sub);
                break;
            case INFO:
                Log.i(tag, sub);
                break;
            case WARN:
                Log.w(tag, sub);
                break;
            case ERROR:
                Log.e(tag, sub);
                break;
            default:
                break;
        }
        printLine(tag, false);
    }

    private static void printJson(String tag, String json, String headString) {
        if (TextUtils.isEmpty(json)) {
            d("Empty/Null json content");
            return;
        }
        if (TextUtils.isEmpty(tag)) {
            tag = ContentValues.TAG;
        }
        String message;

        try {
            if (json.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(json);
                message = jsonObject.toString(JSON_INDENT);
            } else if (json.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(json);
                message = jsonArray.toString(JSON_INDENT);
            } else {
                message = json;
            }
        } catch (JSONException e) {
            message = json;
        }

        printLine(tag, true);
        message = headString + LINE_SEPARATOR + message;
        String[] lines = message.split(LINE_SEPARATOR);
        for (String line : lines) {
            Log.d(tag, "|" + line);
        }
        printLine(tag, false);
    }

    private static void printXml(String tag, String xml, String headString) {
        if (xml != null) {
            try {
                Source xmlInput = new StreamSource(new StringReader(xml));
                StreamResult xmlOutput = new StreamResult(new StringWriter());
                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
                transformer.transform(xmlInput, xmlOutput);
                xml = xmlOutput.getWriter().toString().replaceFirst(">", ">\n");
            } catch (Exception e) {
                e.printStackTrace();
            }
            xml = headString + "\n" + xml;
        } else {
            xml = headString + "Log with null object";
        }

        printLine(tag, true);
        String[] lines = xml.split(LINE_SEPARATOR);
        for (String line : lines) {
            if (!TextUtils.isEmpty(line)) {
                Log.d(tag, "|" + line);
            }
        }
        printLine(tag, false);
    }

    private static void printLine(String tag, boolean isTop) {
        if (isTop) {
            Log.d(tag, "╔═══════════════════════════════════════════════════════════════════════════════════════");
        } else {
            Log.d(tag, "╚═══════════════════════════════════════════════════════════════════════════════════════");
        }
    }

}