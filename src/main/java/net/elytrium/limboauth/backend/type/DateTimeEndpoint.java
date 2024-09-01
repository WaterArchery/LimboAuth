/*
 * Copyright (C) 2021 - 2024 Elytrium
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.elytrium.limboauth.backend.type;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import net.elytrium.limboauth.LimboAuth;
import net.elytrium.limboauth.backend.Endpoint;

import java.time.Instant;
import java.util.Date;
import java.util.function.Function;

public class DateTimeEndpoint extends Endpoint {

  private Date value;
  private Function<String, Date> function;

  public DateTimeEndpoint(LimboAuth plugin, String type, Function<String, Date> function) {
    super(plugin, type, null);
    this.function = function;
  }

  public DateTimeEndpoint(LimboAuth plugin, String type, String username, Date value) {
    super(plugin, type, username);
    this.value = value;
  }

  @Override
  public void writeContents(ByteArrayDataOutput output) {
    output.writeUTF(String.valueOf(this.value.toInstant().getEpochSecond()));
  }

  @Override
  public void readContents(ByteArrayDataInput input) {
    long myValue = this.function.apply(this.username).getTime();
    this.value = Date.from(Instant.ofEpochMilli(myValue));
  }

  @Override
  public String toString() {
    return "DateTimeEndpoint{"
        + "username='" + this.username + '\''
        + ", value=" + this.value
        + '}';
  }
}
