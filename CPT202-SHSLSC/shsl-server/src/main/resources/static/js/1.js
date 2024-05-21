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

        displayInfoBox();
      });
    }

    document.body.appendChild(timeBox);
  }

  function displayInfoBox() {
    const oldInfoBox = document.querySelector('.info-box');
    if (oldInfoBox) {
      oldInfoBox.remove();
    }

    const infoBox = document.createElement('div');
    infoBox.classList.add('info-box');

    const prices = ['$2', '$3', '$4', '$4', '$3', '$4', '$2', '$3', '$4']; 

    for (let i = 0; i < 9; i++) {
      const infoItem = document.createElement('div');
      infoItem.classList.add('info-item');
      infoItem.textContent = `space ${i + 1}`; 
      
      const status = document.createElement('div');
      status.classList.add('status');
      status.textContent = 'free'; 
      infoItem.appendChild(status);

      const priceSpan = document.createElement('span');
      priceSpan.classList.add('price');
      priceSpan.textContent = prices[i];
      infoItem.appendChild(priceSpan);

      const button = document.createElement('button');
      button.classList.add('info-button');
      button.textContent = 'order'; 
      button.addEventListener('click', function() {
        if(!document.querySelector('.confirm-box')) {
        const confirmBox = document.createElement('div');
        confirmBox.classList.add('confirm-box');
        confirmBox.innerHTML = `
          <div class="message">Confirm your choice?</div>
          <button class="confirm">Confiem</button>
          <button class="cancel">Cancel</button>
        `;
        document.body.appendChild(confirmBox);

        const confirmButton = confirmBox.querySelector('.confirm');
        confirmButton.addEventListener('click', function() {
          console.log('Confirmed');
          //到订单页面
          window.location.href = '#';
          confirmBox.remove(); 
        });

        const cancelButton = confirmBox.querySelector('.cancel');
        cancelButton.addEventListener('click', function() {
          console.log('Cancelled');
          confirmBox.remove();
        });
      }
      });

      infoItem.appendChild(button);

      infoBox.appendChild(infoItem);
    }

    document.body.appendChild(infoBox);
  }
});
