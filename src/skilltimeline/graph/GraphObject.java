package skilltimeline.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Abstract base class for all drawable objects. An object has an (X, Y)
 * position and can contain child objects.
 */
public abstract class GraphObject {

	protected List<GraphObject> _children = new ArrayList<GraphObject>();
	protected final GraphObject _parent;
	protected Position _pos;
	protected String _strokecolour;
	protected String _fillcolour;

	/**
	 * Encodes an absolute or relative position in 2D (x, y).
	 * 
	 * Hard to believe the JDK doesn't have an XY position class, isn't it?
	 * (Without importing the SWING package.)
	 */
	public static final class Position {
		// doubles for laying out at any scale, but usually code should use int
		// values
		final double x, y;

		public Position(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}

	public GraphObject(GraphObject parent) {
		_parent = parent;
		if (_parent != null)
			_parent.addChild(this);
	}

	public final GraphObject position(Position p) {
		this._pos = p;
		return this;
	}

	public final Position getPosition() {
		return _pos;
	}

	public final String getStrokeColour() {
		return _strokecolour;
	}

	public final GraphObject strokeColour(String c) {
		this._strokecolour = c;
		return this;
	}

	public final String getFillColour() {
		return _fillcolour;
	}

	public final GraphObject fillColour(String c) {
		this._fillcolour = c;
		return this;
	}

	public final GraphObject addChild(GraphObject g) {
		this._children.add(g);
		return this;
	}

	public final GraphObject removeChild(GraphObject g) {
		this._children.remove(g);
		return this;
	}

	public final List<GraphObject> getChildren() {
		return Collections.unmodifiableList(_children);
	}

	// Subclasses implement elements of the graph: the canvas, lines, rects,
	// text labels

	public static class GCanvas extends GraphObject {
		public GCanvas(GraphObject parent) {
			super(parent);
		}
	}

	public static class GLabel extends GraphObject {
		String Text, Font;
		int Size;

		public GLabel(GraphObject parent, String text, String colour, String font, int size) {
			super(parent);
			strokeColour(colour);
			fillColour(colour);
			Text = text;
			Font = font;
			Size = size;
		}
	}

	public static class GRectangle extends GraphObject {
		Position Size;

		public GRectangle(GraphObject parent, String strokeColour, String fillColour, Position size) {
			super(parent);
			this.Size = size;
			strokeColour(strokeColour);
			fillColour(fillColour);
		}
	}

	public static class GLine extends GraphObject {
		Position Size;
		int StrokeWidth;

		public GLine(GraphObject parent, Position size, String colour, int strokeWidth) {
			super(parent);
			Size = size;
			StrokeWidth = strokeWidth;
			fillColour(colour);
			strokeColour(colour);
		}
	}
}
