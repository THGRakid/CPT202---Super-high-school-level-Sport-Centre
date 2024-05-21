
const words = ["like Art", "Creativity", "Life", "Everything"],
      dynamicText = document.querySelector("h1 span");


let wordIndex = 0,
    charIndex = 1,
    isDeleting = false;

function typingEffect() {
  const currentWord = words[wordIndex];
  const currentChar = currentWord.substring(0, charIndex);


  dynamicText.textContent = currentChar;
  dynamicText.classList.add("stop-blinking");


  if (!isDeleting && charIndex < currentWord.length) {
    charIndex++;
    
    setTimeout(typingEffect, 200);
  } else if (isDeleting && charIndex > 0) {


    charIndex--;
    
    setTimeout(typingEffect, 200);
  } else {


    isDeleting = !isDeleting;
    wordIndex = !isDeleting ? (wordIndex + 1) % words.length : wordIndex;

    dynamicText.classList.remove("stop-blinking");
    setTimeout(typingEffect, 200);
  }
};

typingEffect();
