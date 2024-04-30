package com.backend.controller.admin;
import com.backend.entity.PredictionForm;
import org.python.util.PythonInterpreter;

import java.util.HashMap;
import java.util.Map;

import org.python.core.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class PredictionController {

    @PostMapping("/predict")
    public Double predictSalary(@RequestBody PredictionForm predictionForm) {
        // Process the user's input data
        Map<String, Object> data = new HashMap<>();
        data.put("experience", predictionForm.getExperience());
        data.put("level_number", predictionForm.getLevelNumber());
        data.put("Python", predictionForm.getPython());
        data.put("Java", predictionForm.getJava());
        data.put("JavaScript", predictionForm.getJavaScript());
        data.put("C++", predictionForm.getCpp());
        data.put("C#", predictionForm.getCsharp());
        data.put("PHP", predictionForm.getPhp());
        data.put("Ruby", predictionForm.getRuby());
        data.put("Swift", predictionForm.getSwift());
        data.put("TypeScript", predictionForm.getTypeScript());
        data.put("Go", predictionForm.getGo());
        data.put("Koltin", predictionForm.getKotlin());
        data.put("Rust", predictionForm.getRust());
        data.put("Lua", predictionForm.getLua());
        data.put("Perl", predictionForm.getPerl());
        data.put("SQL", predictionForm.getSql());
        data.put("HTML", predictionForm.getHtml());
        data.put("CSS", predictionForm.getCss());
        data.put("R", predictionForm.getR());
        data.put("Matlab", predictionForm.getMatlab());
        data.put("Shell", predictionForm.getShell());
        data.put("Assembly", predictionForm.getAssembly());
        data.put(".NET", predictionForm.getDotNet());
        data.put("C", predictionForm.getC());
//        data.put("Tiếng Anh", predictionForm.getTiengAnh());
//        data.put("Tiếng Nhật ", predictionForm.getTiengNhat());
//        data.put("Tiếng Trung", predictionForm.getTiengTrung());
        data.put("Web", predictionForm.getWeb());
        data.put("Android", predictionForm.getAndroid());
        data.put("iOS", predictionForm.getIos());
        data.put("Backend", predictionForm.getBackend());
        data.put("FrontEnd", predictionForm.getFrontend());
        data.put("Machine Learning", predictionForm.getMachineLearning());
        data.put("Data", predictionForm.getData());
        data.put("Game", predictionForm.getGame());
        data.put("Embedded", predictionForm.getEmbedded());
        data.put("Network", predictionForm.getNetwork());
        data.put("Computer Science", predictionForm.getComputerScience());
        data.put("Software", predictionForm.getSoftware());
        data.put("Security", predictionForm.getSecurity());
        data.put("Robot", predictionForm.getRobot());
        data.put("Cloud", predictionForm.getCloud());
        data.put("AI", predictionForm.getAi());
        data.put("Nhúng", predictionForm.getNhung());
        data.put("Bridge", predictionForm.getBridge());
        data.put("Software.1", predictionForm.getSoftware1());
        data.put("Designer", predictionForm.getDesigner());
        data.put("Scrum", predictionForm.getScrum());
        data.put("BrSE", predictionForm.getBrse());
        data.put("Tester", predictionForm.getTester());
        data.put("Comtor", predictionForm.getComtor());
        data.put("location_number", predictionForm.getLocationNumber());

        PyDictionary pyData = new PyDictionary();

        for (Map.Entry<String, Object> entry : data.entrySet()) {
            pyData.__setitem__(new PyString(entry.getKey()), PyJavaType.wrapJavaObject(entry.getValue()));
        }

        try (PythonInterpreter pyInterp = new PythonInterpreter()) {
            pyInterp.execfile("C:\\Users\\nguye\\OneDrive - The University of Technology\\GITHUB CLONE REPO\\PBL3-Applying-AI-to-analyze-recruitment-information-on-Facebook\\DataAnalysis\\script.py");
            PyObject predictFunc = pyInterp.get("predict");
            PyObject result = predictFunc.__call__(pyData);
            return result.asDouble();
        }
    }
}
