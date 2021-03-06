/*
 * Copyright (C) 2014-2016 Helix Engine Developers 
 * (http://github.com/fauu/HelixEngine)
 *
 * This software is licensed under the GNU General Public License
 * (version 3 or later). See the COPYING file in this distribution.
 *
 * You should have received a copy of the GNU Library General Public License
 * along with this software. If not, see <http://www.gnu.org/licenses/>.
 *
 * Authored by: Piotr Grabowski <fau999@gmail.com>
 */

package com.github.fauu.helix.datum;

import com.github.fauu.helix.TilePermission;
import com.github.fauu.helix.util.IntVector2;

public class Tile {

  private TilePermission permissions;

  private TileAreaPassage areaPassage;

  public Tile() {
    setPermissions(TilePermission.LEVEL0);
  }

  public TilePermission getPermissions() {
    return permissions;
  }

  public void setPermissions(TilePermission permissions) {
    this.permissions = permissions;
  }

  public TileAreaPassage getAreaPassage() {
    return areaPassage;
  }

  public void setAreaPassage(TileAreaPassage passage) {
    areaPassage = passage;
  }

  public static Tile get(Tile[][] tiles, IntVector2 coords) {
    return tiles[coords.y][coords.x];
  }

}
