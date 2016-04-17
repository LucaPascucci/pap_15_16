package ass03.sol;

import ass03.P2d;
import ass03.PointCloud;

import java.util.function.Consumer;


public interface ExtPointCloud extends PointCloud {

	void apply(Consumer<P2d> t);
}
