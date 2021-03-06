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

package com.github.fauu.helix.displayable;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

public class AreaDisplayable extends ModelDisplayable {

  public AreaDisplayable(Model model) {
    instance = new ModelInstance(model);

    animationController = new AnimationController(instance);

    instance.transform.rotate(new Vector3(1, 0, 0), 90);

    for (Material material : instance.materials) {
      TextureAttribute ta
          = (TextureAttribute) material.get(TextureAttribute.Diffuse);

      ta.textureDescription.magFilter = Texture.TextureFilter.Nearest;
      ta.textureDescription.minFilter = Texture.TextureFilter.Nearest;

      material.set(ta);
      material.set(ColorAttribute.createDiffuse(Color.WHITE));

      BlendingAttribute ba = new BlendingAttribute(GL20.GL_SRC_ALPHA,
                                                   GL20.GL_ONE_MINUS_SRC_ALPHA);

      material.set(ba);
    }
  }

  @Override
  public void getRenderables(Array<Renderable> renderables,
                             Pool<Renderable> pool) {
    instance.getRenderables(renderables, pool);
  }

}
