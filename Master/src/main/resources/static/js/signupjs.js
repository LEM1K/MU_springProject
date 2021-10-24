const instaimg = document.querySelector(".insta_img");
const githubimg = document.querySelector(".github_img");

function instaOnClick() {
    window.open("https://www.instagram.com/l_mn_k/");
};

function githubOnClick() {
    window.open("https://github.com/LEM1K");
};


instaimg.addEventListener("click", instaOnClick);
githubimg.addEventListener("click", githubOnClick);
