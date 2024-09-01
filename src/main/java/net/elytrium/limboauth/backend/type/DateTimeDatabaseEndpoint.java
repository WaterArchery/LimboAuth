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

import net.elytrium.limboauth.LimboAuth;
import net.elytrium.limboauth.handler.AuthSessionHandler;
import net.elytrium.limboauth.model.RegisteredPlayer;

import java.time.Instant;
import java.util.Date;
import java.util.function.Function;

public class DateTimeDatabaseEndpoint extends DateTimeEndpoint {

  public DateTimeDatabaseEndpoint(LimboAuth plugin, String type, String username, Date value) {
    super(plugin, type, username, value);
  }

  public DateTimeDatabaseEndpoint(LimboAuth plugin, String type, Function<RegisteredPlayer, Date> function) {
    super(plugin, type, username -> {
      RegisteredPlayer player = AuthSessionHandler.fetchInfo(plugin.getPlayerDao(), username);
      if (player == null) {
        return Date.from(Instant.now());
      } else {
        return function.apply(player);
      }
    });
  }
}
