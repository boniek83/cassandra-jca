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

    @Override
    public void truncate(String string) throws InvalidRequestException, UnavailableException, TimedOutException, TException {
        iface.truncate(string);
    }

    public String system_update_keyspace(KsDef ksdef) throws InvalidRequestException, SchemaDisagreementException, TException {
        return iface.system_update_keyspace(ksdef);
    }

    public String system_update_column_family(CfDef cfdef) throws InvalidRequestException, SchemaDisagreementException, TException {
        return iface.system_update_column_family(cfdef);
    }

    public String system_drop_keyspace(String string) throws InvalidRequestException, SchemaDisagreementException, TException {
        return iface.system_drop_keyspace(string);
    }

    public String system_drop_column_family(String string) throws InvalidRequestException, SchemaDisagreementException, TException {
        return iface.system_drop_column_family(string);
    }

    public String system_add_keyspace(KsDef ksdef) throws InvalidRequestException, SchemaDisagreementException, TException {
        return iface.system_add_keyspace(ksdef);
    }

    public String system_add_column_family(CfDef cfdef) throws InvalidRequestException, SchemaDisagreementException, TException {
        return iface.system_add_column_family(cfdef);
    }

    public void set_keyspace(String string) throws InvalidRequestException, TException {
        iface.set_keyspace(string);
    }

    public void remove_counter(ByteBuffer bb, ColumnPath cp, ConsistencyLevel cl) throws InvalidRequestException, UnavailableException, TimedOutException, TException {
        iface.remove_counter(bb, cp, cl);
    }

    public void remove(ByteBuffer bb, ColumnPath cp, long l, ConsistencyLevel cl) throws InvalidRequestException, UnavailableException, TimedOutException, TException {
        iface.remove(bb, cp, l, cl);
    }

    public Map<ByteBuffer, List<ColumnOrSuperColumn>> multiget_slice(List<ByteBuffer> list, ColumnParent cp, SlicePredicate sp, ConsistencyLevel cl) throws InvalidRequestException, UnavailableException, TimedOutException, TException {
        return iface.multiget_slice(list, cp, sp, cl);
    }

    public Map<ByteBuffer, Integer> multiget_count(List<ByteBuffer> list, ColumnParent cp, SlicePredicate sp, ConsistencyLevel cl) throws InvalidRequestException, UnavailableException, TimedOutException, TException {
        return iface.multiget_count(list, cp, sp, cl);
    }

    public void login(AuthenticationRequest ar) throws AuthenticationException, AuthorizationException, TException {
        iface.login(ar);
    }

    public void insert(ByteBuffer bb, ColumnParent cp, Column column, ConsistencyLevel cl) throws InvalidRequestException, UnavailableException, TimedOutException, TException {
        iface.insert(bb, cp, column, cl);
    }

    public List<ColumnOrSuperColumn> get_slice(ByteBuffer bb, ColumnParent cp, SlicePredicate sp, ConsistencyLevel cl) throws InvalidRequestException, UnavailableException, TimedOutException, TException {
        return iface.get_slice(bb, cp, sp, cl);
    }

    public List<KeySlice> get_range_slices(ColumnParent cp, SlicePredicate sp, KeyRange kr, ConsistencyLevel cl) throws InvalidRequestException, UnavailableException, TimedOutException, TException {
        return iface.get_range_slices(cp, sp, kr, cl);
    }

    public List<KeySlice> get_indexed_slices(ColumnParent cp, IndexClause ic, SlicePredicate sp, ConsistencyLevel cl) throws InvalidRequestException, UnavailableException, TimedOutException, TException {
        return iface.get_indexed_slices(cp, ic, sp, cl);
    }

    public int get_count(ByteBuffer bb, ColumnParent cp, SlicePredicate sp, ConsistencyLevel cl) throws InvalidRequestException, UnavailableException, TimedOutException, TException {
        return iface.get_count(bb, cp, sp, cl);
    }

    public ColumnOrSuperColumn get(ByteBuffer bb, ColumnPath cp, ConsistencyLevel cl) throws InvalidRequestException, NotFoundException, UnavailableException, TimedOutException, TException {
        return iface.get(bb, cp, cl);
    }

    public CqlResult execute_cql_query(ByteBuffer bb, Compression cmprsn) throws InvalidRequestException, UnavailableException, TimedOutException, SchemaDisagreementException, TException {
        return iface.execute_cql_query(bb, cmprsn);
    }

    public String describe_version() throws TException {
        return iface.describe_version();
    }

    public List<String> describe_splits(String string, String string1, String string2, int i) throws InvalidRequestException, TException {
        return iface.describe_splits(string, string1, string2, i);
    }

    public String describe_snitch() throws TException {
        return iface.describe_snitch();
    }

    public Map<String, List<String>> describe_schema_versions() throws InvalidRequestException, TException {
        return iface.describe_schema_versions();
    }

    public List<TokenRange> describe_ring(String string) throws InvalidRequestException, TException {
        return iface.describe_ring(string);
    }

    public String describe_partitioner() throws TException {
        return iface.describe_partitioner();
    }

    public List<KsDef> describe_keyspaces() throws InvalidRequestException, TException {
        return iface.describe_keyspaces();
    }

    public KsDef describe_keyspace(String string) throws NotFoundException, InvalidRequestException, TException {
        return iface.describe_keyspace(string);
    }

    public String describe_cluster_name() throws TException {
        return iface.describe_cluster_name();
    }

    public void batch_mutate(Map<ByteBuffer, Map<String, List<Mutation>>> map, ConsistencyLevel cl) throws InvalidRequestException, UnavailableException, TimedOutException, TException {
        iface.batch_mutate(map, cl);
    }

    public void add(ByteBuffer bb, ColumnParent cp, CounterColumn cc, ConsistencyLevel cl) throws InvalidRequestException, UnavailableException, TimedOutException, TException {
        iface.add(bb, cp, cc, cl);
    }

    public void setIface(Cassandra.Iface iface) {
        this.iface = iface;
    }

    @Override
    public List<KeySlice> get_paged_slice(String string, KeyRange kr, ByteBuffer bb, ConsistencyLevel cl) throws InvalidRequestException, UnavailableException, TimedOutException, TException {
        return iface.get_paged_slice(string, kr, bb, cl);
    }

    @Override
    public Map<String, String> describe_token_map() throws InvalidRequestException, TException {
        return iface.describe_token_map();
    }

    @Override
    public List<CfSplit> describe_splits_ex(String string, String string1, String string2, int i) throws InvalidRequestException, TException {
        return iface.describe_splits_ex(string, string1, string2, i);
    }

    @Override
    public CqlPreparedResult prepare_cql_query(ByteBuffer bb, Compression cmprsn) throws InvalidRequestException, TException {
        return iface.prepare_cql_query(bb, cmprsn);
    }

    @Override
    public CqlResult execute_prepared_cql_query(int i, List<ByteBuffer> list) throws InvalidRequestException, UnavailableException, TimedOutException, SchemaDisagreementException, TException {
        return iface.execute_prepared_cql_query(i, list);
    }

    @Override
    public void set_cql_version(String string) throws InvalidRequestException, TException {
        iface.set_cql_version(string);
    }

    @Override
    public void atomic_batch_mutate(Map<ByteBuffer, Map<String, List<Mutation>>> mutation_map, ConsistencyLevel consistency_level) throws InvalidRequestException, UnavailableException, TimedOutException, TException {
        iface.atomic_batch_mutate(mutation_map, consistency_level);
    }

    @Override
    public ByteBuffer trace_next_query() throws TException {
        return iface.trace_next_query();
    }

    @Override
    public CqlResult execute_cql3_query(ByteBuffer query, Compression compression, ConsistencyLevel consistency) throws InvalidRequestException, UnavailableException, TimedOutException, SchemaDisagreementException, TException {
        return iface.execute_cql3_query(query, compression, consistency);
    }

    @Override
    public CqlPreparedResult prepare_cql3_query(ByteBuffer query, Compression compression) throws InvalidRequestException, TException {
        return iface.prepare_cql3_query(query, compression);
    }

    @Override
    public CqlResult execute_prepared_cql3_query(int itemId, List<ByteBuffer> values, ConsistencyLevel consistency) throws InvalidRequestException, UnavailableException, TimedOutException, SchemaDisagreementException, TException {
        return iface.execute_prepared_cql3_query(itemId, values, consistency);
    }
}
