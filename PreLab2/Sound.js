// Predefined valid credentials (multiple users supported)
const validCredentials = [
    {
        username: 'admin',
        password: 'password123'
    },
    {
        username: 'Mohammad Faissal A. Undug',
        password: 'PerpFai_IT5'
    },
    {
        username: 'Val Patrick Fabregas',
        password: 'GOATED_IT'
    }
];

// Store attendance records in memory
let attendanceRecords = [];

// Create beep sound using Web Audio API
function playBeep() {
    const audioContext = new (window.AudioContext || window.webkitAudioContext)();
    const oscillator = audioContext.createOscillator();
    const gainNode = audioContext.createGain();

    oscillator.connect(gainNode);
    gainNode.connect(audioContext.destination);

    // Higher pitch beep for error
    oscillator.frequency.value = 900;
    oscillator.type = 'sine';

    gainNode.gain.setValueAtTime(0.4, audioContext.currentTime);
    gainNode.gain.exponentialRampToValueAtTime(0.01, audioContext.currentTime + 0.6);

    oscillator.start(audioContext.currentTime);
    oscillator.stop(audioContext.currentTime + 0.6);
}

// Format timestamp
function getFormattedTimestamp() {
    const now = new Date();
    const month = String(now.getMonth() + 1).padStart(2, '0');
    const day = String(now.getDate()).padStart(2, '0');
    const year = now.getFullYear();
    const hours = String(now.getHours()).padStart(2, '0');
    const minutes = String(now.getMinutes()).padStart(2, '0');
    const seconds = String(now.getSeconds()).padStart(2, '0');

    return `${month}/${day}/${year} ${hours}:${minutes}:${seconds}`;
}

// Show message with animation
function showMessage(text, type) {
    const messageDiv = document.getElementById('message');
    messageDiv.textContent = text;
    messageDiv.className = `message ${type}`;
    messageDiv.style.display = 'block';

    setTimeout(() => {
        messageDiv.style.display = 'none';
    }, 4000);
}

// Add attendance record
function addAttendanceRecord(username, timestamp) {
    const record = {
        username: username,
        timestamp: timestamp
    };
    attendanceRecords.push(record);
    updateAttendanceList();
}

// Update attendance list display
function updateAttendanceList() {
    const recordsDiv = document.getElementById('records');
    const listDiv = document.getElementById('attendanceList');

    if (attendanceRecords.length > 0) {
        listDiv.style.display = 'block';
        
        // Display records in reverse order (newest first)
        const recordsHTML = attendanceRecords
            .slice()
            .reverse()
            .map((record, index) => {
                return `
                    <div class="attendance-item">
                        <strong>${record.username}</strong> - ${record.timestamp}
                    </div>
                `;
            })
            .join('');
        
        recordsDiv.innerHTML = recordsHTML;
    }
}

// Generate and download attendance summary
function downloadAttendanceSummary() {
    let content = '=================================================\n';
    content += '          ATTENDANCE SUMMARY REPORT\n';
    content += '=================================================\n\n';
    content += 'Login Records:\n';
    content += '-------------------------------------------------\n\n';

    attendanceRecords.forEach((record, index) => {
        content += `${index + 1}. Username: ${record.username}\n`;
        content += `   Login Time: ${record.timestamp}\n\n`;
    });

    content += '-------------------------------------------------\n';
    content += `Total Login Sessions: ${attendanceRecords.length}\n`;
    content += `Report Generated: ${getFormattedTimestamp()}\n`;
    content += '=================================================\n';

    // Create blob and trigger download
    const blob = new Blob([content], { type: 'text/plain' });
    const link = document.createElement('a');
    link.href = window.URL.createObjectURL(blob);
    link.download = 'attendance_summary.txt';
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
}

// Handle form submission
document.getElementById('loginForm').addEventListener('submit', function(e) {
    e.preventDefault();

    const username = document.getElementById('username').value.trim();
    const password = document.getElementById('password').value;

    // Validate credentials - check against all valid users
    const validUser = validCredentials.find(
        user => user.username === username && user.password === password
    );

    if (validUser) {
        // Successful login
        const timestamp = getFormattedTimestamp();
        
        // Add to attendance records
        addAttendanceRecord(username, timestamp);

        // Display success message
        showMessage('✓ Login successful! Welcome back.', 'success');

        // Display timestamp section
        document.getElementById('loggedUser').textContent = username;
        document.getElementById('timestamp').textContent = timestamp;
        document.getElementById('timestampSection').style.display = 'block';

        // Clear form
        document.getElementById('loginForm').reset();
    } else {
        // Failed login - play beep sound
        playBeep();
        showMessage('✗ Invalid username or password! Please try again.', 'error');
        
        // Hide timestamp section
        document.getElementById('timestampSection').style.display = 'none';
        
        // Clear password field
        document.getElementById('password').value = '';
    }
});

// Download button event
document.getElementById('downloadBtn').addEventListener('click', function() {
    if (attendanceRecords.length > 0) {
        downloadAttendanceSummary();
        showMessage('✓ Attendance summary downloaded successfully!', 'success');
    } else {
        showMessage('✗ No attendance records available to download.', 'error');
    }
});

// Add enter key support for better UX
document.getElementById('password').addEventListener('keypress', function(e) {
    if (e.key === 'Enter') {
        document.getElementById('loginForm').dispatchEvent(new Event('submit'));
    }
});