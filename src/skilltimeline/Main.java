package skilltimeline;

import skilltimeline.graph.GraphObject;
import skilltimeline.graph.SvgRenderer;
import skilltimeline.graph.GraphObject.GCanvas;
import skilltimeline.graph.GraphObject.GLabel;
import skilltimeline.graph.GraphObject.GLine;
import skilltimeline.graph.GraphObject.Position;

/**
 * Main entry point to the application: parses command line and provides app
 * runtime.
 */
public class Main {

	public static void main(String[] args) {

		GraphObject canvas = new GCanvas(null);
		GLine line = new GLine(canvas, new Position(100, 100), "red", 5);
		line.position(new Position(50, 50));
		GLabel lbl = new GLabel(canvas, "the quick brown fox", "black", "Geneva, Tahoma", 12);
		lbl.position(new Position(40, 40));

		StringBuilder sb = new StringBuilder();
		SvgRenderer.getRenderer(canvas).render(canvas, sb);
		System.out.println(sb);
	}
}
