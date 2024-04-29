package com.backend.controller.admin;
import com.backend.entity.PredictionForm;
import org.python.util.PythonInterpreter;
import org.python.core.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;
@Controller
public class PredictionController {

    @PostMapping("/predict")
    public Double predictSalary(@RequestBody PredictionForm predictionForm) {
        // Process the user's input data
        Map<String, Object> data = new HashMap<>();
        data.put("experience", predictionForm.getExperience());
        data.put("level_number", predictionForm.getLevelNumber());
        data.put("python", predictionForm.getPython());
        data.put("java", predictionForm.getJava());
        data.put("javaScript", predictionForm.getJavaScript());
        data.put("cpp", predictionForm.getCpp());
        data.put("csharp", predictionForm.getCsharp());
        data.put("php", predictionForm.getPhp());
        data.put("ruby", predictionForm.getRuby());
        data.put("swift", predictionForm.getSwift());
        data.put("typeScript", predictionForm.getTypeScript());
        data.put("go", predictionForm.getGo());
        data.put("kotlin", predictionForm.getKotlin());
        data.put("rust", predictionForm.getRust());
        data.put("lua", predictionForm.getLua());
        data.put("perl", predictionForm.getPerl());
        data.put("sql", predictionForm.getSql());
        data.put("html", predictionForm.getHtml());
        data.put("css", predictionForm.getCss());
        data.put("r", predictionForm.getR());
        data.put("matlab", predictionForm.getMatlab());
        data.put("shell", predictionForm.getShell());
        data.put("assembly", predictionForm.getAssembly());
        data.put("dotNet", predictionForm.getDotNet());
        data.put("c", predictionForm.getC());
        data.put("tiengAnh", predictionForm.getTiengAnh());
        data.put("tiengNhat", predictionForm.getTiengNhat());
        data.put("tiengTrung", predictionForm.getTiengTrung());
        data.put("web", predictionForm.getWeb());
        data.put("android", predictionForm.getAndroid());
        data.put("ios", predictionForm.getIos());
        data.put("backend", predictionForm.getBackend());
        data.put("frontend", predictionForm.getFrontend());
        data.put("machineLearning", predictionForm.getMachineLearning());
        data.put("data", predictionForm.getData());
        data.put("game", predictionForm.getGame());
        data.put("embedded", predictionForm.getEmbedded());
        data.put("network", predictionForm.getNetwork());
        data.put("computerScience", predictionForm.getComputerScience());
        data.put("software", predictionForm.getSoftware());
        data.put("security", predictionForm.getSecurity());
        data.put("robot", predictionForm.getRobot());
        data.put("cloud", predictionForm.getCloud());
        data.put("ai", predictionForm.getAi());
        data.put("nhung", predictionForm.getNhung());
        data.put("bridge", predictionForm.getBridge());
        data.put("software1", predictionForm.getSoftware1());
        data.put("designer", predictionForm.getDesigner());
        data.put("scrum", predictionForm.getScrum());
        data.put("brse", predictionForm.getBrse());
        data.put("tester", predictionForm.getTester());
        data.put("comtor", predictionForm.getComtor());
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
