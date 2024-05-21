document.addEventListener("DOMContentLoaded", function() {
  const buttons = document.querySelectorAll('.sport-button');
  
  buttons.forEach(button => {
    button.addEventListener('click', function() {
      buttons.forEach(btn => btn.classList.remove('active'));
      this.classList.add('active');
      displayTimeBox();
    });
  });

  function displayTimeBox() {
    const oldTimeBox = document.querySelector('.time-box');
    if (oldTimeBox) {
      oldTimeBox.remove(); 
    }
    
    const today = new Date();
    const days = ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'];

    const timeBox = document.createElement('div');
    timeBox.classList.add('time-box');

    for (let i = 0; i < 7; i++) {
      const date = new Date(today);
      date.setDate(today.getDate() + i);

      const dayOfWeek = days[date.getDay()];
      const dayOfMonth = date.getDate();
      const month = date.getMonth() + 1;

      const dateElement = document.createElement('button');
      dateElement.classList.add('time-button');
      dateElement.textContent = `${dayOfWeek}, ${month}/${dayOfMonth}`;
      timeBox.appendChild(dateElement);

      dateElement.addEventListener('click', function() {
        const selectedDate = document.querySelector('.time-button.active');
        if (selectedDate) {
          selectedDate.classList.remove('active');
        }
        this.classList.add('active');



      });
    }

    document.body.appendChild(timeBox);
  }
});
