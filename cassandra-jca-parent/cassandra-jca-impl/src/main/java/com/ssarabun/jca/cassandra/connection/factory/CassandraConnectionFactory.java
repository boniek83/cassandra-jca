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
package com.ssarabun.jca.cassandra.connection.factory;

import javax.naming.NamingException;
import javax.naming.Reference;
import javax.resource.ResourceException;
import javax.resource.spi.ConnectionManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ssarabun.jca.cassandra.api.CassandraConnection;

/**
 * 
 * @author sergey.sarabun@gmail.com
 * @date Apr 21, 2013
 */
public class CassandraConnectionFactory implements com.ssarabun.jca.cassandra.api.CassandraConnectionFactory {

    private final static Logger logger = LoggerFactory.getLogger(CassandraConnectionFactory.class);
    private static final long serialVersionUID = 1L;
    private CassandraManagedConnectionFactory mcf;
    private ConnectionManager cxManager;
    private Reference reference;

    public CassandraConnectionFactory(CassandraManagedConnectionFactory mcf, ConnectionManager cxManager) {
        this.mcf = mcf;
        this.cxManager = cxManager;
    }

    public CassandraConnection getConnection() throws ResourceException {
        return (CassandraConnection) cxManager.allocateConnection(mcf, null);
    }

    public void setReference(Reference reference) {
        this.reference = reference;
    }

    public Reference getReference() throws NamingException {
        return reference;
    }
}
