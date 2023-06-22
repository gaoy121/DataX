package com.alibaba.datax.plugin.writer.neo4jwriter.config;


import java.util.List;

/**
 * @author fuyouj
 */
public final class ConfigConstants {

    public static final Long DEFAULT_MAX_TRANSACTION_RETRY_SECONDS = 30L;

    public static final Long DEFAULT_MAX_CONNECTION_SECONDS = 30L;



    public static final Option<Integer> RETRY_TIMES =
            Option.<Integer>builder()
                    .key("retry_times")
                    .defaultValue(3)
                    .desc("The number of overwrites when an error occurs")
                    .build();

    public static final Option<Long> RETRY_SLEEP_MILLS =
            Option.<Long>builder()
                    .key("retry_sleep_mills")
                    .defaultValue(3000L)
                    .build();

    /**
     * cluster mode please reference
     * <a href="https://neo4j.com/docs/java-manual/current/client-applications/">how to connect cluster mode</a>
     */
    public static final Option<String> URI =
            Option.<String>builder()
                    .key("uri")
                    .noDefaultValue()
                    .desc("uir of neo4j database")
                    .build();

    public static final Option<String> USERNAME =
            Option.<String>builder()
                    .key("username")
                    .noDefaultValue()
                    .desc("username for accessing the neo4j database")
                    .build();

    public static final Option<String> PASSWORD =
            Option.<String>builder()
                    .key("password")
                    .noDefaultValue()
                    .desc("password for accessing the neo4j database")
                    .build();

    public static final Option<String> BEARER_TOKEN =
            Option.<String>builder()
                    .key("bearer_token")
                    .noDefaultValue()
                    .desc("base64 encoded bearer token of the Neo4j. for Auth.")
                    .build();

    public static final Option<String> KERBEROS_TICKET =
            Option.<String>builder()
                    .key("kerberos_ticket")
                    .noDefaultValue()
                    .desc("base64 encoded kerberos ticket of the Neo4j. for Auth.")
                    .build();

    public static final Option<String> DATABASE =
            Option.<String>builder()
                    .key("database")
                    .noDefaultValue()
                    .desc("database name.")
                    .build();

    public static final Option<String> CYPHER =
            Option.<String>builder()
                    .key("cypher")
                    .noDefaultValue()
                    .desc("cypher query.")
                    .build();

    public static final Option<Long> MAX_TRANSACTION_RETRY_TIME =
            Option.<Long>builder()
                    .key("max_transaction_retry_time_seconds")
                    .defaultValue(DEFAULT_MAX_TRANSACTION_RETRY_SECONDS)
                    .desc("maximum transaction retry time(seconds). transaction fail if exceeded.")
                    .build();
    public static final Option<Long> MAX_CONNECTION_TIMEOUT_SECONDS =
            Option.<Long>builder()
                    .key("max_connection_timeout_seconds")
                    .defaultValue(DEFAULT_MAX_CONNECTION_SECONDS)
                    .desc("The maximum amount of time to wait for a TCP connection to be established (seconds).")
                    .build();

    public static final Option<String> BATCH_DATA_VARIABLE_NAME =
            Option.<String>builder()
                    .key("batch_data_variable_name")
                    .defaultValue("batch")
                    .desc("In a cypher statement, a variable name that represents a batch of data")
                    .build();

    public static final Option<List<Neo4jField>> NEO4J_FIELDS =
            Option.<List<Neo4jField>>builder()
                    .key("fields")
                    .noDefaultValue()
                    .desc("neo4j fields.")
                    .build();

    public static final Option<Integer> BATCH_SIZE =
            Option.<Integer>builder().
                    key("batch_size")
                    .defaultValue(1000)
                    .desc("max batch size")
                    .build();
}
