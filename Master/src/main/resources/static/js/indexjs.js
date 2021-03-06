window.onload = function() {
    onloadTitleTransition();
    onloadBackTransition();
    onloadTextTransition();
};

function onloadTitleTransition() {
    const titletxt = document.querySelector(".title_txt");
    titletxt.style.color = "black";
    titletxt.style.fontSize = "2.5rem";
}

function onloadBackTransition() {
    const contentBack = document.querySelector(".content");
    const btnsBack = document.querySelector(".btns");
    contentBack.style.backgroundColor = "mediumslateblue";
    btnsBack.style.backgroundColor = "mediumslateblue";

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
