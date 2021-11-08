window.onload = function() {
    onloadTitleTransition();
    onloadNavTransition();
    onloadTableTransition();
};


function onloadNavTransition() {
    const nav = document.querySelector(".navi_list")
    const home = document.querySelector(".home");
    const general = document.querySelector(".general");
    const column = document.querySelector(".column");
    const composite = document.querySelector(".composite");
    const chat = document.querySelector(".chat");

    nav.style.width = "200px";
    nav.style.height = "700px";

    home.style.fontSize = "1.2rem";
    general.style.fontSize = "1.2rem";
    column.style.fontSize = "1.2rem";
    composite.style.fontSize = "1.2rem";
    chat.style.fontSize = "1.2rem";
}

function onloadTableTransition() {
    const table = document.querySelector(".list_table");

    table.style.width = "100%";
}


function onloadTitleTransition() {
    const titletxt = document.querySelector(".title_txt");
    titletxt.style.color = "black";
    titletxt.style.fontSize = "2.5rem";
}


function onloadTextTransition() {
    const welcometxt = document.querySelector(".welcome_txt");
    welcometxt.style.color = "white";
    welcometxt.style.fontSize = "5rem";
    welcometxt.style.backgroundColor = "mediumslateblue";
}

const instaimg = document.querySelector(".insta_img");
const githubimg = document.querySelector(".github_img");

function instaOnClick() {
    window.open("https://www.instagram.com/l_mn_k/");
}

function githubOnClick() {
    window.open("https://github.com/LEM1K");
}


instaimg.addEventListener("click", instaOnClick);
githubimg.addEventListener("click", githubOnClick);