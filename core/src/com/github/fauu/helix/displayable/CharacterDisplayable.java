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

import com.artemis.annotations.Wire;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.github.fauu.helix.Direction;
import com.github.fauu.helix.graphics.AnimatedDecal;
import com.github.fauu.helix.graphics.AnimationSet;
import com.github.fauu.helix.graphics.AnimationType;
import com.github.fauu.helix.util.IntVector3;

public class CharacterDisplayable extends DecalDisplayable {

  @Wire
  private AssetManager assetManager;

  protected AnimationSet animations;

  public CharacterDisplayable(IntVector3 logicalPosition,
                              String animationSetName,
                              TextureRegion shadowTexture) {
    super(logicalPosition.toVector3().add(DEFAULT_DISPLACEMENT));

    animations = new AnimationSet(animationSetName);

    AnimatedDecal decal
        = AnimatedDecal.newAnimatedDecal(DEFAULT_DIMENSIONS.x,
                                         DEFAULT_DIMENSIONS.y,
                                         animations.getDefault(),
                                         true);

    decal.setKeepSize(true);
    decal.setPosition(position);
    decal.rotateX(DEFAULT_ROTATION);

    setMainDecal(decal);

    Decal shadow = new Decal();

    shadow.setPosition(position);
    shadow.translate(DEFAULT_SHADOW_DISPLACEMENT);
    shadow.setDimensions(DEFAULT_SHADOW_DIMENSIONS.x,
                         DEFAULT_SHADOW_DIMENSIONS.y);
    shadow.setColor(1, 1, 1, .35f);
    shadow.setTextureRegion(shadowTexture);
    shadow.setBlending(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

    setShadowDecal(shadow);
  }

  @Override
  public void animate(AnimationType type, Direction direction, float duration) {
    AnimatedDecal animatedDecal = ((AnimatedDecal) this.mainDecal);

    animatedDecal.stop();
    animatedDecal.setAnimated(animations.get(type, direction, duration));
    animatedDecal.play();
  }

  @Override
  public void orientate(Direction direction) {
    AnimatedDecal animatedDecal = ((AnimatedDecal) this.mainDecal);

    animatedDecal.setAnimated(animations.get(AnimationType.IDLE, direction));
    animatedDecal.play();
  }

}
