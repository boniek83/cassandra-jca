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

import java.io.PrintWriter;
import java.util.Set;
import javax.resource.ResourceException;
import javax.resource.spi.ConnectionManager;
import javax.resource.spi.ConnectionRequestInfo;
import javax.resource.spi.ManagedConnection;
import javax.resource.spi.ManagedConnectionFactory;
import javax.resource.spi.ResourceAdapter;
import javax.resource.spi.ResourceAdapterAssociation;
import javax.resource.spi.ValidatingManagedConnectionFactory;
import javax.security.auth.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author sergey.sarabun@gmail.com
 * @date Apr 21, 2013
 */
public class CassandraManagedConnectionFactory
        extends CassandraProperties
        implements ManagedConnectionFactory,
        ValidatingManagedConnectionFactory,
        ResourceAdapterAssociation {

    private static final Logger logger = LoggerFactory.getLogger(CassandraManagedConnectionFactory.class);
    private static final long serialVersionUID = 1L;
    private PrintWriter out;
    private ResourceAdapter ra;

    public Object createConnectionFactory(ConnectionManager cxManager) throws ResourceException {
        return new CassandraConnectionFactory(this, cxManager);
    }

    public Object createConnectionFactory() throws ResourceException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ManagedConnection createManagedConnection(Subject subject, ConnectionRequestInfo cxRequestInfo) throws ResourceException {
        return new CassandraManagedConnection(this);
    }

    public ManagedConnection matchManagedConnections(Set connectionSet, Subject subject, ConnectionRequestInfo cxRequestInfo) throws ResourceException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setLogWriter(PrintWriter out) throws ResourceException {
        this.out = out;
    }

    public PrintWriter getLogWriter() throws ResourceException {
        return out;
    }

    public ResourceAdapter getResourceAdapter() {
        return ra;
    }

    public void setResourceAdapter(ResourceAdapter ra) throws ResourceException {
        this.ra = ra;
    }

    public Set getInvalidConnections(Set connectionSet) throws ResourceException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CassandraManagedConnectionFactory other = (CassandraManagedConnectionFactory) obj;
        if (this.ra != other.ra && (this.ra == null || !this.ra.equals(other.ra))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.ra != null ? this.ra.hashCode() : 0);
        return hash;
    }
}
