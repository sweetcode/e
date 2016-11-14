import de.SweetCode.e.input.InputEntry;
import de.SweetCode.e.rendering.GameScene;
import de.SweetCode.e.rendering.layers.Layers;
import de.SweetCode.e.resources.textures.TextureLoader;

import java.awt.*;
import java.util.Random;

public class TestScene extends GameScene {

    private TextureLoader loader;
    private int delta = 0;

    public TestScene(TextureLoader loader) {
        this.loader = loader;
    }

    Random random = new Random();

    @Override
    public void render(Layers layers) {

        layers.first().g().setColor(Color.YELLOW);
        layers.first().g().fillRect(50, 50, 500, 500);

        for(int i = 0; i < 100; i++) layers.first().g().drawImage(loader.get(delta), 100, 100, null);
    }

    @Override
    public void update(InputEntry input, long delta) {
        this.delta++;
        if(this.delta > 42) this.delta = 0;
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
