package com.project.comic.notice;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Service
public class NoticeService {

	@Autowired
	private NoticeDAO nDao;

	public int AddNotice(MultipartHttpServletRequest files, HttpServletRequest req, HttpSession session,
			NoticeDTO ndto) {
		List<MultipartFile> mfile = files.getFiles("img");
		String imgpath = "noticeimg";
		String filePath = req.getSession().getServletContext().getRealPath("/resources/");
		String fullPath = filePath + imgpath;
		String originname = "";
		String fakename = "";
		System.out.println("file size:" + mfile.size());

		
		if(mfile.size()>0) {
			File Folder = new File(fullPath);
			// 해당 디렉토리가 없을경우 디렉토리를 생성합니다.
			if (!Folder.exists()) {
				try {
					Folder.mkdir(); // 폴더 생성합니다.
					System.out.println("폴더가 생성되었습니다.");
				} catch (Exception e) {
					e.getStackTrace();
				}
			} else {
				System.out.println("이미 폴더가 생성되어 있습니다.");
			}

			System.out.println("full path:" + fullPath);


			for (MultipartFile mf : mfile) {
				String originFileName = mf.getOriginalFilename(); // 원본 파일 명
				
				long fileSize = mf.getSize(); // 파일 사이즈

				if (fileSize > 0) {
					String safeFile = fullPath + "/" + System.currentTimeMillis() + originFileName;
					originname+=originFileName+",";
					fakename+=System.currentTimeMillis() + originFileName+",";
					try {
						mf.transferTo(new File(safeFile));
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}else {
					System.out.println("전송된 파일이 없습니다.");
				}
				System.out.println("originFileName : " + originFileName);
				System.out.println("fileSize : " + fileSize);
			}
			ndto.setUidx((int)session.getAttribute("uidx"));
			ndto.setOriginimg(originname);
			ndto.setFakeimg(fakename);
			return nDao.addNotice(ndto);
			
		}else {
			return nDao.addNotice(ndto);
		}
		

	}

}
