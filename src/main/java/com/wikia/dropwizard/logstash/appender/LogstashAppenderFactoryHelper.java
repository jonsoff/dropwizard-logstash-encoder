package com.wikia.dropwizard.logstash.appender;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;
import net.logstash.logback.fieldnames.LogstashFieldNames;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class LogstashAppenderFactoryHelper {

  public static String defaultValueIfEmpty(String value, String defaultValue) {
    if (StringUtils.isEmpty(value)) {
      return defaultValue;
    } else {
      return value;
    }
  }

  public static LogstashFieldNames getFieldNamesFromHashMap(HashMap<String, String> map) {
    LogstashFieldNames fieldNames = new LogstashFieldNames();

    fieldNames.setTimestamp(defaultValueIfEmpty(map.get("timestamp"), "@timestamp"));
    fieldNames.setVersion(defaultValueIfEmpty(map.get("version"), "@version"));
    fieldNames.setMessage(defaultValueIfEmpty(map.get("message"), "message"));
    fieldNames.setLogger(defaultValueIfEmpty(map.get("logger"), "logger_name"));
    fieldNames.setThread(defaultValueIfEmpty(map.get("thread"), "thread_name"));
    fieldNames.setLevel(defaultValueIfEmpty(map.get("level"), "level"));
    fieldNames.setLevelValue(defaultValueIfEmpty(map.get("levelValue"), "level_value"));
    fieldNames.setCaller(defaultValueIfEmpty(map.get("caller"), null));
    fieldNames.setCallerClass(defaultValueIfEmpty(map.get("callerClass"), "caller_class_name"));
    fieldNames.setCallerMethod(defaultValueIfEmpty(map.get("callerMethod"), "caller_method_name"));
    fieldNames.setCallerFile(defaultValueIfEmpty(map.get("callerFile"), "caller_file_name"));
    fieldNames.setCallerLine(defaultValueIfEmpty(map.get("callerLine"), "caller_line_number"));
    fieldNames.setStackTrace(defaultValueIfEmpty(map.get("stackTrace"), "stack_trace"));
    fieldNames.setTags(defaultValueIfEmpty(map.get("tags"), "tags"));
    fieldNames.setMdc(defaultValueIfEmpty(map.get("mdc"), null));
    fieldNames.setContext(defaultValueIfEmpty(map.get("context"), null));

    return fieldNames;
  }

  public static String getCustomFieldsFromHashMap(HashMap<String, String> map) throws IOException {
    StringWriter writer = new StringWriter();
    ObjectMapper mapper = new ObjectMapper();
    mapper.writeValue(writer, map);
    return writer.toString();
  }
}
