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

import javax.resource.ResourceException;
import javax.resource.spi.ManagedConnectionMetaData;

/**
 * 
 * @author sergey.sarabun@gmail.com
 * @date Jun 24, 2013
 */
public class CassandraManagedConnectionMetaData implements ManagedConnectionMetaData {

    public String getEISProductName() throws ResourceException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getEISProductVersion() throws ResourceException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getMaxConnections() throws ResourceException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getUserName() throws ResourceException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
