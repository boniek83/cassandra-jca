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
package com.ssarabun.jca.cassandra.connection;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;

import org.apache.cassandra.thrift.AuthenticationException;
import org.apache.cassandra.thrift.AuthenticationRequest;
import org.apache.cassandra.thrift.AuthorizationException;
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

import com.ssarabun.jca.cassandra.connection.exception.ClosedCassandraIfaceException;

/**
 * 
 * @author sergey.sarabun@gmail.com
 * @date Jul 15, 2013
 */
public class ClosedCassandraIface implements Cassandra.Iface {

    public void login(AuthenticationRequest auth_request) throws AuthenticationException, AuthorizationException, TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public void set_keyspace(String keyspace) throws InvalidRequestException, TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public ColumnOrSuperColumn get(ByteBuffer key, ColumnPath column_path, ConsistencyLevel consistency_level) throws InvalidRequestException, NotFoundException, UnavailableException, TimedOutException, TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public List<ColumnOrSuperColumn> get_slice(ByteBuffer key, ColumnParent column_parent, SlicePredicate predicate, ConsistencyLevel consistency_level) throws InvalidRequestException, UnavailableException, TimedOutException, TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public int get_count(ByteBuffer key, ColumnParent column_parent, SlicePredicate predicate, ConsistencyLevel consistency_level) throws InvalidRequestException, UnavailableException, TimedOutException, TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public Map<ByteBuffer, List<ColumnOrSuperColumn>> multiget_slice(List<ByteBuffer> keys, ColumnParent column_parent, SlicePredicate predicate, ConsistencyLevel consistency_level) throws InvalidRequestException, UnavailableException, TimedOutException, TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public Map<ByteBuffer, Integer> multiget_count(List<ByteBuffer> keys, ColumnParent column_parent, SlicePredicate predicate, ConsistencyLevel consistency_level) throws InvalidRequestException, UnavailableException, TimedOutException, TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public List<KeySlice> get_range_slices(ColumnParent column_parent, SlicePredicate predicate, KeyRange range, ConsistencyLevel consistency_level) throws InvalidRequestException, UnavailableException, TimedOutException, TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public List<KeySlice> get_paged_slice(String column_family, KeyRange range, ByteBuffer start_column, ConsistencyLevel consistency_level) throws InvalidRequestException, UnavailableException, TimedOutException, TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public List<KeySlice> get_indexed_slices(ColumnParent column_parent, IndexClause index_clause, SlicePredicate column_predicate, ConsistencyLevel consistency_level) throws InvalidRequestException, UnavailableException, TimedOutException, TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public void insert(ByteBuffer key, ColumnParent column_parent, Column column, ConsistencyLevel consistency_level) throws InvalidRequestException, UnavailableException, TimedOutException, TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public void add(ByteBuffer key, ColumnParent column_parent, CounterColumn column, ConsistencyLevel consistency_level) throws InvalidRequestException, UnavailableException, TimedOutException, TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public void remove(ByteBuffer key, ColumnPath column_path, long timestamp, ConsistencyLevel consistency_level) throws InvalidRequestException, UnavailableException, TimedOutException, TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public void remove_counter(ByteBuffer key, ColumnPath path, ConsistencyLevel consistency_level) throws InvalidRequestException, UnavailableException, TimedOutException, TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public void batch_mutate(Map<ByteBuffer, Map<String, List<Mutation>>> mutation_map, ConsistencyLevel consistency_level) throws InvalidRequestException, UnavailableException, TimedOutException, TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public void atomic_batch_mutate(Map<ByteBuffer, Map<String, List<Mutation>>> mutation_map, ConsistencyLevel consistency_level) throws InvalidRequestException, UnavailableException, TimedOutException, TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public void truncate(String cfname) throws InvalidRequestException, UnavailableException, TimedOutException, TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public Map<String, List<String>> describe_schema_versions() throws InvalidRequestException, TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public List<KsDef> describe_keyspaces() throws InvalidRequestException, TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public String describe_cluster_name() throws TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public String describe_version() throws TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public List<TokenRange> describe_ring(String keyspace) throws InvalidRequestException, TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public Map<String, String> describe_token_map() throws InvalidRequestException, TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public String describe_partitioner() throws TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public String describe_snitch() throws TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public KsDef describe_keyspace(String keyspace) throws NotFoundException, InvalidRequestException, TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public List<String> describe_splits(String cfName, String start_token, String end_token, int keys_per_split) throws InvalidRequestException, TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public ByteBuffer trace_next_query() throws TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public List<CfSplit> describe_splits_ex(String cfName, String start_token, String end_token, int keys_per_split) throws InvalidRequestException, TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public String system_add_column_family(CfDef cf_def) throws InvalidRequestException, SchemaDisagreementException, TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public String system_drop_column_family(String column_family) throws InvalidRequestException, SchemaDisagreementException, TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public String system_add_keyspace(KsDef ks_def) throws InvalidRequestException, SchemaDisagreementException, TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public String system_drop_keyspace(String keyspace) throws InvalidRequestException, SchemaDisagreementException, TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public String system_update_keyspace(KsDef ks_def) throws InvalidRequestException, SchemaDisagreementException, TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public String system_update_column_family(CfDef cf_def) throws InvalidRequestException, SchemaDisagreementException, TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public CqlResult execute_cql_query(ByteBuffer query, Compression compression) throws InvalidRequestException, UnavailableException, TimedOutException, SchemaDisagreementException, TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public CqlResult execute_cql3_query(ByteBuffer query, Compression compression, ConsistencyLevel consistency) throws InvalidRequestException, UnavailableException, TimedOutException, SchemaDisagreementException, TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public CqlPreparedResult prepare_cql_query(ByteBuffer query, Compression compression) throws InvalidRequestException, TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public CqlPreparedResult prepare_cql3_query(ByteBuffer query, Compression compression) throws InvalidRequestException, TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public CqlResult execute_prepared_cql_query(int itemId, List<ByteBuffer> values) throws InvalidRequestException, UnavailableException, TimedOutException, SchemaDisagreementException, TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public CqlResult execute_prepared_cql3_query(int itemId, List<ByteBuffer> values, ConsistencyLevel consistency) throws InvalidRequestException, UnavailableException, TimedOutException, SchemaDisagreementException, TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public void set_cql_version(String version) throws InvalidRequestException, TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }
}
