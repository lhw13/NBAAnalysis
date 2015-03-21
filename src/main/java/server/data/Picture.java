package server.data;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;

import javax.swing.*;

import org.apache.batik.swing.JSVGCanvas;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;

public class Picture {
	public ImageIcon getTeamPic(String abbreviation) {
		File file = new File("./transferStation/transferPic.png");
		FileOutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(file);
			convertToPng(getSYGCode(abbreviation), outputStream);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ImageIcon image = new ImageIcon("./transferStation/transferPic.png");
		return image;
	}

	static private String getSYGCode(String abbreviation) {
		String code = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(
					"./nba/teams/" + abbreviation + ".svg"));
			String line = "";
			while ((line = br.readLine()) != null) {
				code = code + line + "\r\n";
				;
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return code;
	}

	static private void convertToPng(String svgCode, OutputStream outputStream)
			throws Exception {
		byte[] bytes = svgCode.getBytes("utf-8");
		PNGTranscoder t = new PNGTranscoder();
		TranscoderInput input = new TranscoderInput(new ByteArrayInputStream(
				bytes));
		TranscoderOutput output = new TranscoderOutput(outputStream);
		t.transcode(input, output);
		outputStream.flush();
	}

	public ImageIcon getPlayerAction(String name) {
		ImageIcon image = new ImageIcon("nba/players/action/" + name + ".png");
		return image;
	}

	public ImageIcon getPlayerPortrait(String name) {
		ImageIcon image = new ImageIcon("nba/players/portrait/" + name + ".png");
		return image;
	}

	public JSVGCanvas getSwing(String abbreviation) {
		JSVGCanvas s = new JSVGCanvas();
		String path = System.getProperty("user.dir");
		s.loadSVGDocument("file:/" + path + "/nba/teams/" + abbreviation
				+ ".svg");
		return s;
	}
}
