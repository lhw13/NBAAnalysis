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

import server.po.MatchPO;
import console.Console;

public final class Picture {
//	public static void main(String[] args) throws Exception {
//		getPNG();
//	}
	public static ImageIcon getTeamPic(String abbreviation) {
//		File file = new File("./conf/transferStation/transferPic.png");
//		FileOutputStream outputStream = null;
//		try {
//			outputStream = new FileOutputStream(file);
//			convertToPng(getSYGCode(abbreviation), outputStream);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		ImageIcon image = new ImageIcon(
				"./conf/transferStation/"+abbreviation+".png");
		return image;
	}

	private static void getPNG() {

		File f = new File(Console.path + "/teams");
		File[] matchesFile = f.listFiles();
		for (File i : matchesFile) {
			String[] name = i.getName().split("\\.");
			if (name.length == 2) {
				String code = "";
				try {
					BufferedReader br = new BufferedReader(new FileReader(i));
					String line = "";
					while ((line = br.readLine()) != null) {
						code = code + line + "\r\n";
						;
					}
					br.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				String abbreviation = name[0];
				File file = new File("./conf/transferStation/"+abbreviation+".png");
				FileOutputStream outputStream = null;
				try {
					outputStream = new FileOutputStream(file);
					convertToPng(code, outputStream);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

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

	public static ImageIcon getPlayerAction(String name) {
		name=name.replaceAll("\\d", "");
		ImageIcon image = new ImageIcon("nba/players/action/" + name + ".png");
		return image;
	}

	public static ImageIcon getPlayerPortrait(String name) {
		name=name.replaceAll("\\d", "");
		ImageIcon image = new ImageIcon("nba/players/portrait/" + name + ".png");
		return image;
	}

	public static JSVGCanvas getSwing(String abbreviation) {
		JSVGCanvas s = new JSVGCanvas();
		String path = System.getProperty("user.dir");
		s.loadSVGDocument("file:/" + path + "/nba/teams/" + abbreviation
				+ ".svg");
		return s;
	}
}
