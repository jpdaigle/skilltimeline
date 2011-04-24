package skilltimeline.graph;

import skilltimeline.graph.GraphObject.GCanvas;
import skilltimeline.graph.GraphObject.GLabel;
import skilltimeline.graph.GraphObject.GLine;
import skilltimeline.graph.GraphObject.GRectangle;

/**
 * Renderer for SVG. Get the correct renderer for each object and call it.
 */
public abstract class SvgRenderer {

	public abstract void render(final GraphObject g, final StringBuilder s);

	public static SvgRenderer getRenderer(GraphObject g) {
		if (g instanceof GCanvas) {
			return new GCanvasRenderer();
		} else if (g instanceof GLabel) {
			return new GLabelRenderer();
		} else if (g instanceof GRectangle) {
			return new GRectangleRenderer();
		} else if (g instanceof GLine) {
			return new GLineRenderer();
		}
		return null;
	}

	/**
	 * Render the root SVG element (our canvas).
	 */
	protected static class GCanvasRenderer extends SvgRenderer {
		@Override
		public void render(GraphObject g, StringBuilder s) {
			s.append("<svg xmlns='http://www.w3.org/2000/svg' xmlns:xlink='http://www.w3.org/1999/xlink'>");
			s.append("\n");

			// call render on children
			for (GraphObject child : g.getChildren()) {
				getRenderer(child).render(child, s);
			}

			s.append("</svg>\n");
		}
	}

	/**
	 * Render a text node.
	 */
	protected static class GLabelRenderer extends SvgRenderer {
		@Override
		public void render(GraphObject g, StringBuilder s) {
			GLabel lbl = (GLabel) g;
			s.append(String.format("<text x='%s' y='%s' style='", lbl.getPosition().x, lbl.getPosition().y));

			if (lbl.Font != null)
				s.append(String.format("font-family: %s;", lbl.Font));

			if (lbl.Size != 0)
				s.append(String.format("font-size: %s;", lbl.Size));

			if (lbl.getFillColour() != null)
				s.append(String.format("fill: %s;", lbl.getFillColour()));
			
			s.append("'");
			
			if (lbl.Anchor != null)
				s.append(String.format(" text-anchor='%s' ", lbl.Anchor));
			
			s.append(">").append(lbl.Text).append("</text>\n");
		}
	}

	/**
	 * Renders a rectangle with custom stroke and fill. 
	 */
	protected static class GRectangleRenderer extends SvgRenderer {
		@Override
		public void render(GraphObject g, StringBuilder s) {
			GRectangle r = (GRectangle) g;
			s.append(String.format(
					"<rect x='%s' y='%s' width='%s' height='%s' ", 
					r.getPosition().x, 
					r.getPosition().y,
					r.Size.x, 
					r.Size.y));

			s.append("style='");
			
			if (r.getStrokeColour() != null)
				s.append(String.format("stroke: %s;", r.getStrokeColour()));

			if (r.getFillColour() != null)
				s.append(String.format("fill: %s;", r.getFillColour()));
				
			s.append("' />\n");
		}
	}
	
	/**
	 * Renders a simple line. 
	 */
	protected static class GLineRenderer extends SvgRenderer {
		@Override
		public void render(GraphObject g, StringBuilder s) {
			GLine line = (GLine) g;
			s.append(String.format("<line x1='%s' y1='%s' x2='%s' y2='%s' ", 
					line.getPosition().x,
					line.getPosition().y, 
					line.Size.x + line.getPosition().x, 
					line.Size.y + line.getPosition().y));
			s.append("style='");
			
			if (line.getStrokeColour() != null)
				s.append(String.format("stroke: %s;", line.getStrokeColour()));

			if (line.getFillColour() != null)
				s.append(String.format("fill: %s;", line.getFillColour()));
			
			if (line.StrokeWidth != 0)
				s.append(String.format("stroke-width: %s;", line.StrokeWidth));
				
			s.append("' />\n");
		}
	}

}
