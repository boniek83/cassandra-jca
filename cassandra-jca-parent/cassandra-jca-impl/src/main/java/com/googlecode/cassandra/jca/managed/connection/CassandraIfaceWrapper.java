/*
 *  Copyright (C) 2013 sergey.sarabun@gmail.com.
 * 
 *  This library is free software: you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public 
 *  License as published by the Free Software Foundation, either 
 *  version 3 of the License, or (at your option) any later version.
 * 
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this library.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.googlecode.cassandra.jca.managed.connection;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;
import org.apache.cassandra.thrift.AuthenticationException;
import org.apache.cassandra.thrift.AuthenticationRequest;
import org.apache.cassandra.thrift.AuthorizationException;
import org.apache.cassandra.thrift.CASResult;
import org.apache.cassandra.thrift.Cassandra;
import org.apache.cassandra.thrift.CfDef;
import org.apache.cassandra.thrift.CfSplit;
import org.apache.cassandra.thrift.Column;
import org.apache.cassandra.thrift.ColumnOrSuperColumn;
import org.apache.cassandra.thrift.ColumnParent;
import org.apache.cassandra.thrift.ColumnPath;
import org.apache.cassandra.thrift.Compression;
import org.apache.cassandra.thrift.ConsistencyLevel;
import org.apache.cassandra.thrift.CounterColumn;
import org.apache.cassandra.thrift.CqlPreparedResult;
import org.apache.cassandra.thrift.CqlResult;
import org.apache.cassandra.thrift.IndexClause;
import org.apache.cassandra.thrift.InvalidRequestException;
import org.apache.cassandra.thrift.KeyRange;
import org.apache.cassandra.thrift.KeySlice;
import org.apache.cassandra.thrift.KsDef;
import org.apache.cassandra.thrift.Mutation;
import org.apache.cassandra.thrift.NotFoundException;
import org.apache.cassandra.thrift.SchemaDisagreementException;
import org.apache.cassandra.thrift.SlicePredicate;
import org.apache.cassandra.thrift.TimedOutException;
import org.apache.cassandra.thrift.TokenRange;
import org.apache.cassandra.thrift.UnavailableException;
import org.apache.thrift.TException;

/**
 *
 * @author sergey.sarabun@gmail.com
 * @date Jul 15, 2013
 */
public class CassandraIfaceWrapper implements Cassandra.Iface {

    private Cassandra.Iface iface;

    public CassandraIfaceWrapper(Cassandra.Iface iface) {
        this.iface = iface;
    }

    public void login(AuthenticationRequest auth_request) throws AuthenticationException, AuthorizationException, TException {
        iface.login(auth_request);
    }

    public void set_keyspace(String keyspace) throws InvalidRequestException, TException {
        iface.set_keyspace(keyspace);
    }

    public ColumnOrSuperColumn get(ByteBuffer key, ColumnPath column_path, ConsistencyLevel consistency_level) throws InvalidRequestException, NotFoundException, UnavailableException, TimedOutException, TException {
        return iface.get(key, column_path, consistency_level);
    }

    public List<ColumnOrSuperColumn> get_slice(ByteBuffer key, ColumnParent column_parent, SlicePredicate predicate, ConsistencyLevel consistency_level) throws InvalidRequestException, UnavailableException, TimedOutException, TException {
        return iface.get_slice(key, column_parent, predicate, consistency_level);
    }

    public int get_count(ByteBuffer key, ColumnParent column_parent, SlicePredicate predicate, ConsistencyLevel consistency_level) throws InvalidRequestException, UnavailableException, TimedOutException, TException {
        return iface.get_count(key, column_parent, predicate, consistency_level);
    }

    public Map<ByteBuffer, List<ColumnOrSuperColumn>> multiget_slice(List<ByteBuffer> keys, ColumnParent column_parent, SlicePredicate predicate, ConsistencyLevel consistency_level) throws InvalidRequestException, UnavailableException, TimedOutException, TException {
        return iface.multiget_slice(keys, column_parent, predicate, consistency_level);
    }

    public Map<ByteBuffer, Integer> multiget_count(List<ByteBuffer> keys, ColumnParent column_parent, SlicePredicate predicate, ConsistencyLevel consistency_level) throws InvalidRequestException, UnavailableException, TimedOutException, TException {
        return iface.multiget_count(keys, column_parent, predicate, consistency_level);
    }

    public List<KeySlice> get_range_slices(ColumnParent column_parent, SlicePredicate predicate, KeyRange range, ConsistencyLevel consistency_level) throws InvalidRequestException, UnavailableException, TimedOutException, TException {
        return iface.get_range_slices(column_parent, predicate, range, consistency_level);
    }

    public List<KeySlice> get_paged_slice(String column_family, KeyRange range, ByteBuffer start_column, ConsistencyLevel consistency_level) throws InvalidRequestException, UnavailableException, TimedOutException, TException {
        return iface.get_paged_slice(column_family, range, start_column, consistency_level);
    }

    public List<KeySlice> get_indexed_slices(ColumnParent column_parent, IndexClause index_clause, SlicePredicate column_predicate, ConsistencyLevel consistency_level) throws InvalidRequestException, UnavailableException, TimedOutException, TException {
        return iface.get_indexed_slices(column_parent, index_clause, column_predicate, consistency_level);
    }

    public void insert(ByteBuffer key, ColumnParent column_parent, Column column, ConsistencyLevel consistency_level) throws InvalidRequestException, UnavailableException, TimedOutException, TException {
        iface.insert(key, column_parent, column, consistency_level);
    }

    public void add(ByteBuffer key, ColumnParent column_parent, CounterColumn column, ConsistencyLevel consistency_level) throws InvalidRequestException, UnavailableException, TimedOutException, TException {
        iface.add(key, column_parent, column, consistency_level);
    }

    public CASResult cas(ByteBuffer key, String column_family, List<Column> expected, List<Column> updates, ConsistencyLevel serial_consistency_level, ConsistencyLevel commit_consistency_level) throws InvalidRequestException, UnavailableException, TimedOutException, TException {
        return iface.cas(key, column_family, expected, updates, serial_consistency_level, commit_consistency_level);
    }

    public void remove(ByteBuffer key, ColumnPath column_path, long timestamp, ConsistencyLevel consistency_level) throws InvalidRequestException, UnavailableException, TimedOutException, TException {
        iface.remove(key, column_path, timestamp, consistency_level);
    }

    public void remove_counter(ByteBuffer key, ColumnPath path, ConsistencyLevel consistency_level) throws InvalidRequestException, UnavailableException, TimedOutException, TException {
        iface.remove_counter(key, path, consistency_level);
    }

    public void batch_mutate(Map<ByteBuffer, Map<String, List<Mutation>>> mutation_map, ConsistencyLevel consistency_level) throws InvalidRequestException, UnavailableException, TimedOutException, TException {
        iface.batch_mutate(mutation_map, consistency_level);
    }

    public void atomic_batch_mutate(Map<ByteBuffer, Map<String, List<Mutation>>> mutation_map, ConsistencyLevel consistency_level) throws InvalidRequestException, UnavailableException, TimedOutException, TException {
        iface.atomic_batch_mutate(mutation_map, consistency_level);
    }

    public void truncate(String cfname) throws InvalidRequestException, UnavailableException, TimedOutException, TException {
        iface.truncate(cfname);
    }

    public Map<String, List<String>> describe_schema_versions() throws InvalidRequestException, TException {
        return iface.describe_schema_versions();
    }

    public List<KsDef> describe_keyspaces() throws InvalidRequestException, TException {
        return iface.describe_keyspaces();
    }

    public String describe_cluster_name() throws TException {
        return iface.describe_cluster_name();
    }

    public String describe_version() throws TException {
        return iface.describe_version();
    }

    public List<TokenRange> describe_ring(String keyspace) throws InvalidRequestException, TException {
        return iface.describe_ring(keyspace);
    }

    public List<TokenRange> describe_local_ring(String string) throws InvalidRequestException, TException {
        return iface.describe_local_ring(string);
    }

    public Map<String, String> describe_token_map() throws InvalidRequestException, TException {
        return iface.describe_token_map();
    }

    public String describe_partitioner() throws TException {
        return iface.describe_partitioner();
    }

    public String describe_snitch() throws TException {
        return iface.describe_snitch();
    }

    public KsDef describe_keyspace(String keyspace) throws NotFoundException, InvalidRequestException, TException {
        return iface.describe_keyspace(keyspace);
    }

    public List<String> describe_splits(String cfName, String start_token, String end_token, int keys_per_split) throws InvalidRequestException, TException {
        return iface.describe_splits(cfName, start_token, end_token, keys_per_split);
    }

    public ByteBuffer trace_next_query() throws TException {
        return iface.trace_next_query();
    }

    public List<CfSplit> describe_splits_ex(String cfName, String start_token, String end_token, int keys_per_split) throws InvalidRequestException, TException {
        return iface.describe_splits_ex(cfName, start_token, end_token, keys_per_split);
    }

    public String system_add_column_family(CfDef cf_def) throws InvalidRequestException, SchemaDisagreementException, TException {
        return iface.system_add_column_family(cf_def);
    }

    public String system_drop_column_family(String column_family) throws InvalidRequestException, SchemaDisagreementException, TException {
        return iface.system_drop_column_family(column_family);
    }

    public String system_add_keyspace(KsDef ks_def) throws InvalidRequestException, SchemaDisagreementException, TException {
        return iface.system_add_keyspace(ks_def);
    }

    public String system_drop_keyspace(String keyspace) throws InvalidRequestException, SchemaDisagreementException, TException {
        return iface.system_drop_keyspace(keyspace);
    }

    public String system_update_keyspace(KsDef ks_def) throws InvalidRequestException, SchemaDisagreementException, TException {
        return iface.system_update_keyspace(ks_def);
    }

    public String system_update_column_family(CfDef cf_def) throws InvalidRequestException, SchemaDisagreementException, TException {
        return iface.system_update_column_family(cf_def);
    }

    public CqlResult execute_cql_query(ByteBuffer query, Compression compression) throws InvalidRequestException, UnavailableException, TimedOutException, SchemaDisagreementException, TException {
        return iface.execute_cql_query(query, compression);
    }

    public CqlResult execute_cql3_query(ByteBuffer query, Compression compression, ConsistencyLevel consistency) throws InvalidRequestException, UnavailableException, TimedOutException, SchemaDisagreementException, TException {
        return iface.execute_cql3_query(query, compression, consistency);
    }

    public CqlPreparedResult prepare_cql_query(ByteBuffer query, Compression compression) throws InvalidRequestException, TException {
        return iface.prepare_cql_query(query, compression);
    }

    public CqlPreparedResult prepare_cql3_query(ByteBuffer query, Compression compression) throws InvalidRequestException, TException {
        return iface.prepare_cql3_query(query, compression);
    }

    public CqlResult execute_prepared_cql_query(int itemId, List<ByteBuffer> values) throws InvalidRequestException, UnavailableException, TimedOutException, SchemaDisagreementException, TException {
        return iface.execute_prepared_cql_query(itemId, values);
    }

    public CqlResult execute_prepared_cql3_query(int itemId, List<ByteBuffer> values, ConsistencyLevel consistency) throws InvalidRequestException, UnavailableException, TimedOutException, SchemaDisagreementException, TException {
        return iface.execute_prepared_cql3_query(itemId, values, consistency);
    }

    public void set_cql_version(String version) throws InvalidRequestException, TException {
        iface.set_cql_version(version);
    }

    public void setIface(Cassandra.Iface iface) {
        this.iface = iface;
    }
}
