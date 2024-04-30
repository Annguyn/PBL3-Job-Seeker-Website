package com.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class PredictionForm {
    private int experience;
    private int levelNumber;
    private Integer python;
    private Integer java;
    private Integer javaScript;
    private Integer cpp;
    private Integer csharp;
    private Integer php;
    private Integer ruby;
    private Integer swift;
    private Integer typeScript;
    private Integer go;
    private Integer kotlin;
    private Integer rust;
    private Integer lua;
    private Integer perl;
    private Integer sql;
    private Integer html;
    private Integer css;
    private Integer r;
    private Integer matlab;
    private Integer shell;
    private Integer assembly;
    private Integer dotNet;
    private Integer c;
    private Integer tiengAnh;
    private Integer tiengNhat;
    private Integer tiengTrung;
    private Integer web;
    private Integer android;
    private Integer ios;
    private Integer backend;
    private Integer frontend;
    private Integer machineLearning;
    private Integer data;
    private Integer game;
    private Integer embedded;
    private Integer network;
    private Integer computerScience;
    private Integer software;
    private Integer security;
    private Integer robot;
    private Integer cloud;
    private Integer ai;
    private Integer nhung;
    private Integer bridge;
    private Integer software1;
    private Integer designer;
    private Integer scrum;
    private Integer brse;
    private Integer tester;
    private Integer comtor;
    private int locationNumber;

    public PredictionForm(Post post) {
            this.experience = 0;
            this.levelNumber = 0;
            this.python = 0;
            this.java = 0;
            this.javaScript = 0;
            this.cpp = 0;
            this.csharp = 0;
            this.php = 0;
            this.ruby = 0;
            this.swift = 0;
            this.typeScript = 0;
            this.go = 0;
            this.kotlin = 0;
            this.rust = 0;
            this.lua = 0;
            this.perl = 0;
            this.sql = 0;
            this.html = 0;
            this.css = 0;
            this.r = 0;
            this.matlab = 0;
            this.shell = 0;
            this.assembly = 0;
            this.dotNet = 0;
            this.c = 0;
            this.tiengAnh = 0;
            this.tiengNhat = 0;
            this.tiengTrung = 0;
            this.web = 0;
            this.android = 0;
            this.ios = 0;
            this.backend = 0;
            this.frontend = 0;
            this.machineLearning = 0;
            this.data = 0;
            this.game = 0;
            this.embedded = 0;
            this.network = 0;
            this.computerScience = 0;
            this.software = 0;
            this.security = 0;
            this.robot = 0;
            this.cloud = 0;
            this.ai = 0;
            this.nhung = 0;
            this.bridge = 0;
            this.software1 = 0;
            this.designer = 0;
            this.scrum = 0;
            this.brse = 0;
            this.tester = 0;
            this.comtor = 0;
            this.locationNumber = 0;
        this.experience = post.getExperience();
        this.levelNumber = post.getLevel().getId();
        this.locationNumber = post.getLocation().getId();

        for(ProgramingLanguage language : post.getProgramingLanguages()){
            switch (language.getName()) {
                case "Python":
                    this.python = 1;
                    break;
                case "Java":
                    this.java = 1;
                    break;
                case "JavaScript":
                    this.javaScript = 1;
                    break;
                case "C++":
                    this.cpp = 1;
                    break;
                case "C#":
                    this.csharp = 1;
                    break;
                case "PHP":
                    this.php = 1;
                    break;
                case "Ruby":
                    this.ruby = 1;
                    break;
                case "Swift":
                    this.swift = 1;
                    break;
                case "TypeScript":
                    this.typeScript = 1;
                    break;
                case "Go":
                    this.go = 1;
                    break;
                case "Kotlin":
                    this.kotlin = 1;
                    break;
                case "Rust":
                    this.rust = 1;
                    break;
                case "Lua":
                    this.lua = 1;
                    break;
                case "Perl":
                    this.perl = 1;
                    break;
                case "SQL":
                    this.sql = 1;
                    break;
                case "HTML":
                    this.html = 1;
                    break;
                case "CSS":
                    this.css = 1;
                    break;
                case "R":
                    this.r = 1;
                    break;
                case "Matlab":
                    this.matlab = 1;
                    break;
                case "Shell":
                    this.shell = 1;
                    break;
                case "Assembly":
                    this.assembly = 1;
                    break;
                case ".NET":
                    this.dotNet = 1;
                    break;
                case "C":
                    this.c = 1;
                    break;
                default:
                    break;
            }
        }
        for(Category category : post.getCategories()) {
            switch (category.getName()) {
                case "Web":
                    this.web = 1;
                    break;
                case "Android":
                    this.android = 1;
                    break;
                case "iOS":
                    this.ios = 1;
                    break;
                case "Backend":
                    this.backend = 1;
                    break;
                case "Frontend":
                    this.frontend = 1;
                    break;
                case "Machine Learning":
                    this.machineLearning = 1;
                    break;
                case "Data":
                    this.data = 1;
                    break;
                case "Game":
                    this.game = 1;
                    break;
                case "Embedded":
                    this.embedded = 1;
                    break;
                case "Network":
                    this.network = 1;
                    break;
                case "Computer Science":
                    this.computerScience = 1;
                    break;
                case "Software":
                    this.software = 1;
                    break;
                case "Security":
                    this.security = 1;
                    break;
                case "Robot":
                    this.robot = 1;
                    break;
                case "Cloud":
                    this.cloud = 1;
                    break;
                case "AI":
                    this.ai = 1;
                    break;
                case "Nhung":
                    this.nhung = 1;
                    break;
                case "Bridge":
                    this.bridge = 1;
                    break;
                case "Software1":
                    this.software1 = 1;
                    break;
                case "Designer":
                    this.designer = 1;
                    break;
                case "Scrum":
                    this.scrum = 1;
                    break;
                case "BRSE":
                    this.brse = 1;
                    break;
                case "Tester":
                    this.tester = 1;
                    break;
                case "Comtor":
                    this.comtor = 1;
                    break;
                default:
                    break;
            }
        }
    }

}