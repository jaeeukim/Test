package locs.service;

import java.util.List;

import locs.model.LocsDAO;
import locs.model.LocsDTO;

public class LocsService {
	
	private LocsDAO dao;
	
	public LocsService() {
		dao = new LocsDAO();
	}
	
	public List<LocsDTO> getAll() {
		dao = new LocsDAO();
		List<LocsDTO> datas = dao.searchAll();
		dao.close();
		return datas;
	}

	public LocsDTO getId(String id) {
		return _getId(Integer.parseInt(id));
	}
	
	public LocsDTO getId(int id) {
		return _getId(id);
	}
	
	public LocsDTO getId(LocsDTO locsDto) {
		return _getId(locsDto.getLocId());
	}
	
	
	private LocsDTO _getId(int id) {
		dao = new LocsDAO();
				
		LocsDTO data = dao.searchId(id);
		
		dao.close();
		return data;
	}

	public LocsDTO addLocs(String locId, String streetAd, String posCode, String city, String staPro, String ctrId) {
		dao = new LocsDAO();
		
		LocsDTO locsDto = null;
		
		if(locId.matches("\\d+")) {
			locsDto = new LocsDTO();
			locsDto.setLocId(Integer.parseInt(locId));
			locsDto.setStreetAd(streetAd);
			locsDto.setPosCode(posCode);
			locsDto.setCity(city);
			locsDto.setStaPro(staPro);
			locsDto.setCtrId(ctrId);	
			
			if(dao.searchId(locsDto.getLocId()) != null) { //pk라서 중복값있는지 체크
				locsDto.setLocId(-1);
				dao.rollback();
				dao.close();
				return locsDto;
			}
			
			if(!dao.existCtrId(locsDto.getCtrId())) { //fk라 있는지 체크
				locsDto.setCtrId(null);
				dao.rollback();
				dao.close();
				return locsDto;
			}
			
			boolean isSaved = dao.insertLocs(locsDto);
			if(!isSaved) {
				dao.rollback();
				dao.close();
				return null;
			}
		}
		dao.commit();
		dao.close();
		return locsDto;
	}

	public int modifyLocs(LocsDTO data) {
		dao = new LocsDAO();
		if(!dao.existCtrId(data.getCtrId()))  { //fk에 대한 식
			dao.rollback();
			dao.close();
			return -1;
		}
 		
		boolean isSaved = dao.updateLocs(data);
		if(isSaved) {
			dao.commit();
			dao.close();
			return 1;
		}
		dao.rollback();
		dao.close();
		return 0;
	}

	public int deleteLocs(String locId) {
		dao = new LocsDAO();
		if(dao.searchId(Integer.parseInt(locId)) == null) {
			dao.rollback();
			dao.close();
			return -1; //삭제 할거 없다
		}
		
		boolean result = dao.deleteLocs(Integer.parseInt(locId));
		if(result) {
			dao.commit();
			dao.close();
			return 1;
		}
		dao.rollback();
		dao.close();
		return 0;
	}
	
	
}
