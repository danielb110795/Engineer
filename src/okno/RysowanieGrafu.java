package okno;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.GraphZoomScrollPane;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.CrossoverScalingControl;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ScalingControl;
import edu.uci.ics.jung.visualization.decorators.EdgeShape;
import edu.uci.ics.jung.visualization.decorators.PickableEdgePaintTransformer;
import edu.uci.ics.jung.visualization.decorators.PickableVertexPaintTransformer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;

public class RysowanieGrafu extends JFrame {
	private static final long serialVersionUID = 1L;

	JPanel panelRysowaniaGrafu = null;
	JRadioButton przyciskLine = null;
	JRadioButton przyciskQuad = null;
	JRadioButton przyciskCubic = null;
	JButton przyciskPlus = null;
	JButton przyciskMinus = null;
	Layout<String, String> layout = null;
	VisualizationViewer<String, String> visualizationViewer = null;
	DefaultModalGraphMouse<String, String> graphMouse = null;
	GraphZoomScrollPane zoomPanel = null;
	ScalingControl scalingControl = new CrossoverScalingControl();

	JPanel getPanel() {
		if (panelRysowaniaGrafu == null) {
			panelRysowaniaGrafu = new JPanel(new BorderLayout());
			panelRysowaniaGrafu.add(getGraphZoomScrollPane());
		}
		return panelRysowaniaGrafu;
	}

	GraphZoomScrollPane getGraphZoomScrollPane() {
		if (zoomPanel == null) {
			zoomPanel = new GraphZoomScrollPane(visualizationViewer);
		}
		return zoomPanel;
	}

	JRadioButton getPrzyciskCubic() {
		if (przyciskCubic == null) {
			przyciskCubic = new JRadioButton("CubicCurve");
			przyciskCubic.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						visualizationViewer.getRenderContext().setEdgeShapeTransformer(new EdgeShape.CubicCurve<String, String>());
						visualizationViewer.repaint();
					}
				}
			});
		}
		return przyciskCubic;
	}

	JRadioButton getPrzyciskQuad() {
		if (przyciskQuad == null) {
			przyciskQuad = new JRadioButton("QuadCurve");
			przyciskQuad.setSelected(true);
			przyciskQuad.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						visualizationViewer.getRenderContext().setEdgeShapeTransformer(new EdgeShape.QuadCurve<String, String>());
						visualizationViewer.repaint();
					}
				}
			});
		}
		return przyciskQuad;
	}

	JRadioButton getPrzyciskLine() {
		if (przyciskLine == null) {
			przyciskLine = new JRadioButton("Line");
			przyciskLine.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						visualizationViewer.getRenderContext().setEdgeShapeTransformer(new EdgeShape.Line<String, String>());
						visualizationViewer.repaint();
					}
				}
			});
		}
		return przyciskLine;
	}

	JButton getPrzyciskPlus() {
		if (przyciskPlus == null) {
			przyciskPlus = new JButton("+");
			przyciskPlus.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					scalingControl.scale(visualizationViewer, 1.2f, visualizationViewer.getCenter());
				}
			});
		}
		return przyciskPlus;
	}

	JButton getPrzyciskMinus() {
		if (przyciskMinus == null) {
			przyciskMinus = new JButton("-");
			przyciskMinus.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					scalingControl.scale(visualizationViewer, 1 / 1.2f, visualizationViewer.getCenter());
				}
			});
		}
		return przyciskMinus;
	}

	public RysowanieGrafu(Graph<String, String> graf) {
		JFrame frame = new JFrame();

		Layout<String, String> layout = new CircleLayout<String, String>(graf);
		visualizationViewer = new VisualizationViewer<String, String>(layout, new Dimension(600, 400));
		visualizationViewer.setBackground(Color.white);

		visualizationViewer.getRenderContext().setEdgeLabelTransformer(new Transformer<String, String>() {
			public String transform(String e) {
				return ("KrawêdŸ: " + e.toString());
			}
		});

		visualizationViewer.getRenderContext().setVertexLabelTransformer(new Transformer<String, String>() {
			public String transform(String e) {
				return (e.toString());
			}
		});

		visualizationViewer.getRenderContext().setEdgeDrawPaintTransformer(
				new PickableEdgePaintTransformer<String>(visualizationViewer.getPickedEdgeState(), Color.black, Color.yellow));
		visualizationViewer.getRenderContext().setVertexFillPaintTransformer(
				new PickableVertexPaintTransformer<String>(visualizationViewer.getPickedVertexState(), Color.red, Color.cyan));

		visualizationViewer.setVertexToolTipTransformer(new ToStringLabeller<String>());

		graphMouse = new DefaultModalGraphMouse<String, String>();
		visualizationViewer.setGraphMouse(graphMouse);

		graphMouse.setMode(ModalGraphMouse.Mode.TRANSFORMING);

		panelRysowaniaGrafu = getPanel();
		Box box = Box.createHorizontalBox();

		JPanel panelSkali = new JPanel(new GridLayout(0, 1));
		panelSkali.setBorder(BorderFactory.createTitledBorder("Skala"));
		panelSkali.add(getPrzyciskPlus());
		panelSkali.add(getPrzyciskMinus());

		JPanel panelKrawedzi = new JPanel(new GridLayout(1, 3));
		panelKrawedzi.setBorder(BorderFactory.createTitledBorder("Typ krawêdzi"));
		panelKrawedzi.add(getPrzyciskLine());
		panelKrawedzi.add(getPrzyciskQuad());
		panelKrawedzi.add(getPrzyciskCubic());
		ButtonGroup grupaPrzyciskow = new ButtonGroup();
		grupaPrzyciskow.add(przyciskLine);
		grupaPrzyciskow.add(przyciskQuad);
		grupaPrzyciskow.add(przyciskCubic);
		
		JPanel panelTrybuMyszy = new JPanel(new GridLayout(0, 1));
		panelTrybuMyszy.setBorder(BorderFactory.createTitledBorder("Tryb myszy"));
		panelTrybuMyszy.add(graphMouse.getModeComboBox());

		box.add(panelSkali);
		box.add(panelKrawedzi);
		box.add(panelTrybuMyszy);

		panelRysowaniaGrafu.add(box, BorderLayout.SOUTH);
		frame.add(panelRysowaniaGrafu);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		frame.setVisible(true);
		frame.pack();
	}
}