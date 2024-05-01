const slider = document.querySelector('.slider');
const registrationForm = document.getElementById('registration-form');
const companyForm = document.getElementById('company-form');

function activate(e) {
  const items = document.querySelectorAll('.item');
  e.target.matches('.next') && slider.append(items[0]);
  e.target.matches('.prev') && slider.prepend(items[items.length - 1]);
}

function openRegistrationForm(type) {
  registrationForm.style.display = 'block';
  // You can customize the form based on the 'type' parameter
}

function validateForm() {
  const companyName = document.getElementById('company-name').value;
  const contactPerson = document.getElementById('contact-person').value;
  const companyID = document.getElementById('company-id').value;
  const phoneNumber = document.getElementById('phone-number').value;
  const email = document.getElementById('email').value;
  const address = document.getElementById('address').value;

  // Check if required fields are filled
  if (!companyName || !contactPerson || !companyID || !phoneNumber || !email || !address) {
    alert('Please fill in all required fields.');
    return false;
  }

  // Validate email format
  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  if (!emailRegex.test(email)) {
    alert('Please enter a valid email address.');
    return false;
  }

  // Validate phone number format (10 digits)
  const phoneRegex = /^\d{10}$/;
  if (!phoneRegex.test(phoneNumber)) {
    alert('Please enter a valid 10-digit phone number.');
    return false;
  }

  // You can add more specific validations based on your requirements

  return true;
}


function registerCompany() {
  // Validate the form fields
  if (!validateForm()) {
    return;
  }

  const companyName = document.getElementById('company-name').value;
  const contactPerson = document.getElementById('contact-person').value;
  const companyID = document.getElementById('company-id').value;
  const phoneNumber = document.getElementById('phone-number').value;
  const email = document.getElementById('email').value;
  const address = document.getElementById('address').value;

  const data = {
    companyName: companyName,
    contactPerson: contactPerson,
    companyID: companyID,
    phoneNumber: phoneNumber,
    email: email,
    address: address,
    // Add more data if needed
  };

  // Make a POST request to your backend using axios
  axios.post('your-backend-url', data)
    .then(response => {
      console.log(response.data);
      // Show success message to the user
      alert('Registration successful. We will contact you in 48 hours.');
      registrationForm.style.display = 'none';
    })
    .catch(error => {
      console.error('Error registering company:', error);
      // Show error message to the user
      alert('Registration unsuccessful. Please try again.');
    });
}

document.addEventListener('click', activate, false);

function closeRegistrationForm() {
  // Close the registration form
  registrationForm.style.display = 'none';
}
