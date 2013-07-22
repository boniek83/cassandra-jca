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
package com.googlecode.cassadra.jca.connection;

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

import com.googlecode.cassadra.jca.connection.exception.ClosedCassandraIfaceException;

/**
 * 
 * @author sergey.sarabun@gmail.com
 * @date Jul 15, 2013
 */
public class ClosedCassandraIface implements Cassandra.Iface {

    public void login(AuthenticationRequest ar) throws AuthenticationException, AuthorizationException, org.apache.thrift.TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public void set_keyspace(String string) throws InvalidRequestException, org.apache.thrift.TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public ColumnOrSuperColumn get(ByteBuffer bb, ColumnPath cp, ConsistencyLevel cl) throws InvalidRequestException, NotFoundException, UnavailableException, TimedOutException, org.apache.thrift.TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public List<ColumnOrSuperColumn> get_slice(ByteBuffer bb, ColumnParent cp, SlicePredicate sp, ConsistencyLevel cl) throws InvalidRequestException, UnavailableException, TimedOutException, org.apache.thrift.TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public int get_count(ByteBuffer bb, ColumnParent cp, SlicePredicate sp, ConsistencyLevel cl) throws InvalidRequestException, UnavailableException, TimedOutException, org.apache.thrift.TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public Map<ByteBuffer, List<ColumnOrSuperColumn>> multiget_slice(List<ByteBuffer> list, ColumnParent cp, SlicePredicate sp, ConsistencyLevel cl) throws InvalidRequestException, UnavailableException, TimedOutException, org.apache.thrift.TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public Map<ByteBuffer, Integer> multiget_count(List<ByteBuffer> list, ColumnParent cp, SlicePredicate sp, ConsistencyLevel cl) throws InvalidRequestException, UnavailableException, TimedOutException, org.apache.thrift.TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public List<KeySlice> get_range_slices(ColumnParent cp, SlicePredicate sp, KeyRange kr, ConsistencyLevel cl) throws InvalidRequestException, UnavailableException, TimedOutException, org.apache.thrift.TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public List<KeySlice> get_paged_slice(String string, KeyRange kr, ByteBuffer bb, ConsistencyLevel cl) throws InvalidRequestException, UnavailableException, TimedOutException, org.apache.thrift.TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public List<KeySlice> get_indexed_slices(ColumnParent cp, IndexClause ic, SlicePredicate sp, ConsistencyLevel cl) throws InvalidRequestException, UnavailableException, TimedOutException, org.apache.thrift.TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public void insert(ByteBuffer bb, ColumnParent cp, Column column, ConsistencyLevel cl) throws InvalidRequestException, UnavailableException, TimedOutException, org.apache.thrift.TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public void add(ByteBuffer bb, ColumnParent cp, CounterColumn cc, ConsistencyLevel cl) throws InvalidRequestException, UnavailableException, TimedOutException, org.apache.thrift.TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public void remove(ByteBuffer bb, ColumnPath cp, long l, ConsistencyLevel cl) throws InvalidRequestException, UnavailableException, TimedOutException, org.apache.thrift.TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public void remove_counter(ByteBuffer bb, ColumnPath cp, ConsistencyLevel cl) throws InvalidRequestException, UnavailableException, TimedOutException, org.apache.thrift.TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public void batch_mutate(Map<ByteBuffer, Map<String, List<Mutation>>> map, ConsistencyLevel cl) throws InvalidRequestException, UnavailableException, TimedOutException, org.apache.thrift.TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public void truncate(String string) throws InvalidRequestException, UnavailableException, TimedOutException, org.apache.thrift.TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public Map<String, List<String>> describe_schema_versions() throws InvalidRequestException, org.apache.thrift.TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public List<KsDef> describe_keyspaces() throws InvalidRequestException, org.apache.thrift.TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public String describe_cluster_name() throws org.apache.thrift.TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public String describe_version() throws org.apache.thrift.TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public List<TokenRange> describe_ring(String string) throws InvalidRequestException, org.apache.thrift.TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public Map<String, String> describe_token_map() throws InvalidRequestException, org.apache.thrift.TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public String describe_partitioner() throws org.apache.thrift.TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public String describe_snitch() throws org.apache.thrift.TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public KsDef describe_keyspace(String string) throws NotFoundException, InvalidRequestException, org.apache.thrift.TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public List<String> describe_splits(String string, String string1, String string2, int i) throws InvalidRequestException, org.apache.thrift.TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public List<CfSplit> describe_splits_ex(String string, String string1, String string2, int i) throws InvalidRequestException, org.apache.thrift.TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public String system_add_column_family(CfDef cfdef) throws InvalidRequestException, SchemaDisagreementException, org.apache.thrift.TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public String system_drop_column_family(String string) throws InvalidRequestException, SchemaDisagreementException, org.apache.thrift.TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public String system_add_keyspace(KsDef ksdef) throws InvalidRequestException, SchemaDisagreementException, org.apache.thrift.TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public String system_drop_keyspace(String string) throws InvalidRequestException, SchemaDisagreementException, org.apache.thrift.TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public String system_update_keyspace(KsDef ksdef) throws InvalidRequestException, SchemaDisagreementException, org.apache.thrift.TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public String system_update_column_family(CfDef cfdef) throws InvalidRequestException, SchemaDisagreementException, org.apache.thrift.TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public CqlResult execute_cql_query(ByteBuffer bb, Compression cmprsn) throws InvalidRequestException, UnavailableException, TimedOutException, SchemaDisagreementException, org.apache.thrift.TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public CqlPreparedResult prepare_cql_query(ByteBuffer bb, Compression cmprsn) throws InvalidRequestException, org.apache.thrift.TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public CqlResult execute_prepared_cql_query(int i, List<ByteBuffer> list) throws InvalidRequestException, UnavailableException, TimedOutException, SchemaDisagreementException, org.apache.thrift.TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }

    public void set_cql_version(String string) throws InvalidRequestException, org.apache.thrift.TException {
        throw new ClosedCassandraIfaceException("Connection has closed.");
    }
}
