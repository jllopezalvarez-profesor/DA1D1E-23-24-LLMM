package es.jllopezalvarez.tools.formviewer.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

import es.jllopezalvarez.tools.formviewer.models.FormData;
import es.jllopezalvarez.tools.formviewer.models.FilesFormData;
import es.jllopezalvarez.tools.formviewer.models.FileInfo;

@Controller
@RequestMapping("view-form")
public class FormProcessingController {

    @GetMapping("test-form-get")
    public String testFormGet() {
        return "test-form-get";
    }

    @GetMapping("test-form-post")
    public String testFormPost() {
        return "test-form-post";
    }

    @GetMapping("test-form-files")
    public String testFormFiles() {
        return "test-form-files";
    }

    @GetMapping("test-form-test")
    public String testFormTest() {
        return "test-form-test";
    }

    @GetMapping("get")
    //@RequestMapping(value = "post", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String formGet(HttpServletRequest request, Model model) {
        FormData formData = getFormData(request);
        model.addAttribute(formData);
        return "view-form";
    }

    @RequestMapping(value = "post", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String formPost(HttpServletRequest request, Model model) {
        FormData formData = getFormData(request);
        model.addAttribute(formData);
        return "view-form";
    }

    @RequestMapping(value = "files", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String formPostFiles(HttpServletRequest request, @RequestParam("file") MultipartFile[] files, Model model) {
        List<FileInfo> filesInfo = new ArrayList<>();
        for (MultipartFile file : files) {
            filesInfo.add(new FileInfo(file.getName(), file.getOriginalFilename(), file.getSize(), file.getContentType()));
        }
        FilesFormData formData = new FilesFormData(getFormData(request), filesInfo);
        model.addAttribute(formData);
        return "view-form-files";
    }

    private FormData getFormData(HttpServletRequest request) {
        String requestMethod = request.getMethod();
        HashMap<String, String> requestParameters = new HashMap<>();
        var parameterNames = request.getParameterNames();
        for (Iterator<String> parametersIterator = parameterNames.asIterator(); parametersIterator.hasNext(); ) {
            String parameterName = parametersIterator.next();
            String[] parameterValues = request.getParameterValues(parameterName);
            String parameterValue = parameterValues[0];
            if (parameterValues.length > 1) {
                parameterValue = Arrays.toString(parameterValues);
            }
            requestParameters.put(parameterName, parameterValue);
        }
        return new FormData(requestMethod, requestParameters);
    }


}
