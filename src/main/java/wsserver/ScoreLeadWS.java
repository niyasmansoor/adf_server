package wsserver;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.dom4j.DocumentException;

import utils.XMLUtils;
import model.LeadScore;

public class ScoreLeadWS {

	public LeadScore scoreLead(String filePath, byte[] fileBinary) {

		LeadScore score = new LeadScore();
		try {
			FileOutputStream fos = new FileOutputStream(filePath);
			BufferedOutputStream outputStream = new BufferedOutputStream(fos);
			outputStream.write(fileBinary);
			outputStream.flush();
			outputStream.close();
			File adfFile = new File(filePath);
			score = new XMLUtils().calculateLeadScore(adfFile);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return score;
	}

}
