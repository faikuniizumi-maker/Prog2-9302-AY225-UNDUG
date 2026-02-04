// Mohammad Faissal A. Undug - 19-0143-442
// script.js – Correct CSV parsing (attendance = grade)

const csvData = `
id,first_name,last_name,email,gender,attendance
1071908827,Brier,Wace,bwace0@email.com,Male,88
322285668,Bucky,Udall,budall1@email.com,Male,92
103006406,Haslett,Beaford,hbeaford2@email.com,Male,85
104913048,Shelley,Spring,sspring3@email.com,Female,90
051403517,Marius,Southway,msouthway4@email.com,Male,95
21301869,Katharina,Storch,kstorch5@email.com,Female,89
063115178,Hester,Menendez,hmenendez6@email.com,Female,91
084202442,Shaylynn,Scorthorne,sscorn7@email.com,Female,87
275079882,Madonna,Willatt,mwillatt8@email.com,Female,93
071001041,Bancroft,Padfield,bpadfield9@email.com,Male,86
261170740,Rici,Everard,reverarda@email.com,Male,84
113105478,Lishe,Dashkovich,ldashkovichb@email.com,Female,90
267089712,Alexandrina,Abate,aabatec@email.com,Female,88
410022203,Jordon,Ribbens,jribbensd@email.com,Male,92
21308176,Jenette,Andrassy,jandrassye@email.com,Female,89
122239937,Hamid,Chapell,hchapellf@email.com,Male,91
021109935,Cordy,Crosetto,ccrosettog@email.com,Male,87
110026041,Timbino,Carwin,tcarwinh@email.com,Male,90
`;

// Parse CSV → Array of Objects (3 columns ONLY)
function parseCSV(csv) {
    const lines = csv.trim().split("\n");
    return lines.slice(1).map(line => {
        const data = line.split(",");
        return {
            id: data[0],
            name: `${data[1]} ${data[2]}`, // First + Last name
            grade: data[5]                // Attendance (NUMERIC)
        };
    });
}

let students = parseCSV(csvData);

// Render table
function render() {
    const tbody = document.getElementById("tableBody");
    tbody.innerHTML = "";

    students.forEach((s, i) => {
        tbody.innerHTML += `
            <tr>
                <td>${s.id}</td>
                <td>${s.name}</td>
                <td>${s.grade}</td>
                <td>
                    <button class="delete-btn" onclick="removeStudent(${i})">
                        Delete
                    </button>
                </td>
            </tr>
        `;
    });
}

// Add student
function addStudent() {
    const id = idInput.value.trim();
    const name = nameInput.value.trim();
    const grade = gradeInput.value.trim();

    if (!id || !name || !grade) {
        alert("All fields are required.");
        return;
    }

    if (isNaN(grade)) {
        alert("Grade must be a number.");
        return;
    }

    students.push({ id, name, grade });
    render();

    idInput.value = "";
    nameInput.value = "";
    gradeInput.value = "";
}

// Delete student
function removeStudent(index) {
    students.splice(index, 1);
    render();
}

// Initial load
render();
