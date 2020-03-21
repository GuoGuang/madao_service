package com.codeway.config;

import com.google.common.collect.ImmutableList;
import com.querydsl.core.QueryModifiers;

import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.List;

public final class CustomQueryResults<T> implements Serializable {

    private static final long serialVersionUID = -4591506147471300909L;

    private static final CustomQueryResults<Object> EMPTY = new CustomQueryResults<Object>(
            ImmutableList.of(), Long.MAX_VALUE, 0L, 0L);

    @SuppressWarnings("unchecked")
    public static <T> CustomQueryResults<T> emptyResults() {
        return (CustomQueryResults<T>) EMPTY;
    }

    ;

    private final long limit, offset, total;

    private final List<T> results;

    /**
     * Create a new {@link com.querydsl.core.QueryResults} instance
     *
     * @param results paged results
     * @param limit   used limit
     * @param offset  used offset
     * @param total   total result rows count
     */
    public CustomQueryResults(List<T> results, @Nullable Long limit, @Nullable Long offset, long total) {
        this.limit = limit != null ? limit : Long.MAX_VALUE;
        this.offset = offset != null ? offset : 0L;
        this.total = total;
        this.results = results;
    }

    /**
     * Create a new {@link com.querydsl.core.QueryResults} instance
     *
     * @param results paged results
     * @param mod     limit and offset
     * @param total   total result rows count
     */
    public CustomQueryResults(List<T> results, QueryModifiers mod, long total) {
        this(results, mod.getLimit(), mod.getOffset(), total);
    }

    public CustomQueryResults() {
        this(null, 0L, 0L, 0);
    }

    /**
     * Get the results in List form
     * <p>
     * An empty list is returned for no results.
     *
     * @return results
     */
    public List<T> getResults() {
        return results;
    }

    /**
     * Get the total number of results
     *
     * @return total rows
     */
    public long getTotal() {
        return total;
    }

    /**
     * Return whether there are results in the current query window
     *
     * @return true, if no results where found
     */
    public boolean isEmpty() {
        return results.isEmpty();
    }

    /**
     * Get the limit value used for the query
     *
     * @return applied limit
     */
    public long getLimit() {
        return limit;
    }

    /**
     * Get the offset value used for the query
     *
     * @return applied offset
     */
    public long getOffset() {
        return offset;
    }

}