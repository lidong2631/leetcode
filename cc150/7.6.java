public class Edge {
	enum Type {
		inner, outer, flat
	}
	Piece belongPiece;
	Type type;

	public boolean fitsWith(Edge type) {

	}
}

public class Piece {
	Edge left, right, top, bottom;
	Orientation solvedOrientation;
}

public class Puzzle {
	 Piece[][] pieces;
	 Piece[][] solution;
	 Edge[] inners, outers, flats;
	 Edge[] exposed_edges;	//we solve this by goint in-wards, start with the corner. This is a list of the inside edges.

	 void sort() {
	 	//iterate through all edges, adding each to inners and outers.
	 	//look for the corners. add those to solution.
	 	//add each non-flat edge of the corner to exposed_edges.
	 }

	 void solve() {
	 	foreach edge1 in exposed_edges
	 	{
	 		if(edge1.type==Edge.Type.inner)
	 		{
	 			foreach edge2 in outers
	 			{
	 				if edge1.fitWith(edge2)
	 				{
	 					//we found a mathc!
	 					//remove edge1 from exposed_edge
	 					//add edge2's piece to solution.
	 					//check which edge of edge2 are exposed and add those to exposed_edges.
	 				}
	 			}
	 		}
	 		if(edge1.type==Edge.Type.outer)
	 		{
	 			foreach edge2 in inners
	 			{
	 				if edge1.fitWith(edge2)
	 				{
	 					//we found a mathc!
	 					//remove edge1 from exposed_edge
	 					//add edge2's piece to solution.
	 					//check which edge of edge2 are exposed and add those to exposed_edges.
	 				}
	 			}
	 		}
	 	}
	 }
}