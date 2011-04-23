package skilltimeline.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract base class for all renderable objects. An object has an (X, Y)
 * position and can contain child objects.
 */
public abstract class GraphObject {

	protected List<GraphObject> _children = new ArrayList<GraphObject>();
	protected final GraphObject _parent;
	protected Position _pos;

	/**
	 * Encodes an absolute or relative position in 2D (x, y).
	 * 
	 * Hard to believe the JDK doesn't have an XY position class, isn't it?
	 * (Without importing the SWING package.)
	 */
	public final class Position {
		// doubles for laying out at any scale, but usually code should use int
		// values
		double x, y;

		public Position(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}

	public GraphObject(GraphObject parent) {
		_parent = parent;
	}

	public GraphObject position(Position p) {
		this._pos = p;
		return this;
	}

	public Position getPosition() {
		return _pos;
	}

	public GraphObject addChild(GraphObject g) {
		this._children.add(g);
		return this;
	}

	public GraphObject removeChild(GraphObject g) {
		this._children.remove(g);
		return this;
	}

}
