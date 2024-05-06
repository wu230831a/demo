package com.example.demo.service;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.demo.entity.FormInfo;
import com.example.demo.entity.FormList;
import com.example.demo.repository.FormInfoRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class FormInfoService {

    @Autowired
    private FormInfoRepository formInfoRepository;

    /**
     * 創建一個表單
     * @param form
     */
    @Transactional
    public void createForm(FormInfo form) {
    	List<FormList> formLists = form.getFormList();
    	if (formLists == null || formLists.isEmpty()) {
            throw new IllegalArgumentException("FormList is required.");
        }
        if (formLists.size() > 10) {
            throw new IllegalArgumentException("FormList size cannot exceed 10.");
        }

		FormInfo formInfo = new FormInfo(form.getTitle(),form.getDescription(),form.getApplyEmpId(),form.getReviewerId());
	    List<FormList> formList = new ArrayList<FormList>();
	    for (FormList list : formLists) {
	    	list.setForm(formInfo);
	    	formList.add(list);
	    }
	    formInfo.setFormList(formLists);
	    formInfoRepository.save(formInfo);
    }

    /**
     * 主管取得表單狀態不為待簽核的表單
     * @param admin 主管Id
     * @param pageNumber 第幾頁
     * @return
     */
    @Transactional
    public Page<FormInfo> getAdminCompletedForm(String adminId, Integer pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 10);
        return formInfoRepository.getAdminCompletedForm(adminId, pageable);
    }
    
    @Transactional
    public Page<FormInfo> getAdminPendingForm(String adminId, Integer pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 10);
        return formInfoRepository.getAdminPendingForm(adminId, pageable);
    }

    @Transactional
    public Page<FormInfo> getUserCompletedForm(String userId, Integer pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 10);
        return formInfoRepository.getUserCompletedForm(userId, pageable);
    }
    
    @Transactional
    public Page<FormInfo> getUserPendingForm(String userId, Integer pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 10);
        return formInfoRepository.getUserPendingForm(userId, pageable);
    }

    @Transactional
    public boolean updateForm(Integer formId,Integer status) {
    	
        return false;
    }

    @Transactional
    public boolean deleteForm(Integer formId) {
//        FormInfo form = formInfoRepository.findById(formId).get();
//        if (form != null) {
//            form.(false);
//            return true;
//        }
        return false;
    }

    @Transactional
    public void checkFormTime() {
//        List<FormInfo> forms = formInfoRepository.checkFormTime(new Date());
//        System.out.println(forms.get(0).getName());
    }

}
