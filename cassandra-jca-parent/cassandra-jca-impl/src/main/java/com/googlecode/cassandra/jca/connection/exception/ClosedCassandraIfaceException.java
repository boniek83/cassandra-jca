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
package com.googlecode.cassandra.jca.connection.exception;

/**
 * 
 * @author sergey.sarabun@gmail.com
 * @date Jul 15, 2013
 */
public class ClosedCassandraIfaceException extends RuntimeException {

    public ClosedCassandraIfaceException(Throwable cause) {
        super(cause);
    }

    public ClosedCassandraIfaceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClosedCassandraIfaceException(String message) {
        super(message);
    }

    public ClosedCassandraIfaceException() {
    }
}
