package com.zllh.utils.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: VerificationCode
 * @Description: 验证码生成
 * @author JW
 * @date 2015年11月24日 上午10:53:01
 */
public class VerificationCode {

	// 图片宽
	private static int WIDTH = 80;

	// 图片高
	private static int HEIGHT = 30;

	// 验证码数量
	private static int NUM = 4;

	// 干扰线数量
	private static int LINE = 4;

	private static char[] SHOWTEXT = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
			'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
			'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7',
			'8', '9' };

	/**
	 * 生成随机的验证码
	 * 
	 * @return
	 * @throws Exception
	 */
	public static void generator(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Random r = new Random();
		
		// 图片的内存映像
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

		// 获得画笔对象
		Graphics g = image.getGraphics();
		g.setColor(randomColor(200,250));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(new Color(0,0,0));

		// 用于存储随机生成的验证码
		StringBuffer number = new StringBuffer();

		// 绘制验证码
		for (int i = 0; i < NUM; i++) {
			g.setColor(randomColor(20,200));
			int h = (int) ((HEIGHT * 50 / 100) * r.nextDouble() + (HEIGHT * 50 / 100));
			g.setFont(new Font(null, Font.BOLD | Font.ITALIC, h));
			String ch = String.valueOf(SHOWTEXT[r.nextInt(SHOWTEXT.length)]);
			number.append(ch);
			g.drawString(ch, i * WIDTH / NUM * 90 / 100, h);
		}

		// 绘制干扰线
		for (int i = 0; i <= LINE; i++) {
			g.setColor(randomColor(100,200));
			g.drawLine(r.nextInt(WIDTH), r.nextInt(HEIGHT), r.nextInt(WIDTH), r.nextInt(HEIGHT));
		}
		
		request.getSession().setAttribute("imageCode", number.toString());
		g.dispose();
		response.reset();
		ImageIO.write(image, "jpeg", response.getOutputStream());
	}

	private static Color randomColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255) fc = 255;
		if (bc > 255) bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

}
