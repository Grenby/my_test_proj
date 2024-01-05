package ru.mipt.bit.platformer.game.graphics.renderableImpl;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.game.Healthable;
import ru.mipt.bit.platformer.game.graphics.Renderable;

public class HealthRenderImpl implements Renderable {

    private final Healthable healthable;
    private final Rectangle region;
    private final TextureRegion frame;
    private final TextureRegion health;

    public HealthRenderImpl(Texture frame, Texture health, Rectangle region, Healthable healthable) {
        this.region = region;
        this.healthable = healthable;
        this.frame = new TextureRegion(frame);
        this.health = new TextureRegion(health);
    }

    @Override
    public void render(Batch batch) {
        drawBar(batch);
        drawWindow(batch);
        float regionWidth = region.getWidth();
        float regionHeight = region.getHeight()/10;
    }

    @Override
    public Rectangle geRenderableRectangle() {
        return null;
    }

    private void drawBar(Batch batch){
        float regionWidth = region.getWidth();
        float regionHeight = region.getHeight()/10;
        float k = healthable.getMaxHealth() == 0 || Float.isInfinite(healthable.getMaxHealth()) ? 1 : healthable.getCurrentHealth()/healthable.getMaxHealth();
        batch.draw(health, region.x, region.y + region.height + regionHeight, regionWidth * k, regionHeight);
    }

    private void drawWindow(Batch batch){
        float regionWidth = region.getWidth();
        float regionHeight = region.getHeight()/10;

        float frameWidth = region.getWidth()/50;
        float frameHeight = region.getHeight()/50;

        batch.draw(frame, region.x, region.y + region.height + regionHeight, regionWidth, frameHeight);
        batch.draw(frame, region.x, region.y + region.height + 2*regionHeight - frameHeight, regionWidth, frameHeight);

        batch.draw(frame, region.x, region.y + region.height + regionHeight, frameWidth, regionHeight);
        batch.draw(frame, region.x+ region.width - frameWidth, region.y + regionHeight + region.height, frameWidth, regionHeight);
    }

    @Override
    public void dispose() {
    }
}
