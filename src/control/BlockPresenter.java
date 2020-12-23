
package control;

import model.Block;
import view.BlockDisplay;

public class BlockPresenter implements Block.Observer {

    private final Block block;
    private final BlockDisplay blockDisplay;
    private final int size;

    public BlockPresenter(int size, Block block, BlockDisplay blockDisplay) {
        this.size = size;
        this.block = block;
        this.blockDisplay = blockDisplay;
        this.blockDisplay.on(moved());
        this.block.register(this);
        this.refresh();
    }
    @Override
    public void changed() {
        this.refresh();
    }
    
    private void refresh() {
       blockDisplay.paintBlock((block.x()-1)*size,(Block.MAX - block.y()) *size);
    }

    private BlockDisplay.Moved moved() {
        return new BlockDisplay.Moved() {
            @Override
            public void to(int x, int y) {
                block.moveTo(x/size+1,Block.MAX - y/size);
            }
        };
    }
}
