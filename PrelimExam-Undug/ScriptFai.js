// Mohammad Faissal A. Undug - 19-0143-442
// script.js – Correct CSV parsing (attendance = grade)

const csvData = `
id,first_name,last_name,email,gender,attendance
073900438,Osbourne,Wakenshaw,owakenshaw8@email.com,,43
114924014,Albie,Gierardi,agierardi4@email.com,,64
111901632,Eleen,Pentony,epentony2@email.com,,34
084000084,Arie,Okenden,aokenden4@email.com,,38
272471551,Alica,Muckley,amuckley1@email.com,,62
104900721,Jo,Burleton,jburleton1@email.com,,63
111924392,Cam,Akram,cakram2@email.com,,40
292970744,Celine,Brosoli,cbrosoli4@email.com,,44
107004352,Alan,Belfit,abelfit2@email.com,,46
071108313,Jeanette,Gilvear,jgilvear3@email.com,,47
042204932,Ethelin,MacCathay,emaccathay2@email.com,,24
111914218,Kakalina,Finnick,kfinnick8@email.com,,21
074906059,Mayer,Lorenzetti,mlorenzetti9@email.com,,60
091000080,Selia,Rosenstengel,srosenstengel0@email.com,,44
055002480,Dalia,Tadd,dtadd0@email.com,,63
063101111,Darryl,Doogood,ddoogood1@email.com,,46
071908827,Brier,Wace,bwace7@email.com,,60
322285668,Bucky,Udall,budall8@email.com,,52
103006406,Haslett,Beaford,hbeaford6@email.com,,56
104913048,Shelley,Spring,sspring8@email.com,,56
051403517,Marius,Southway,msouthway7@email.com,,62
021301869,Katharina,Storch,kstorch9@email.com,,35
063115178,Hester,Menendez,hmenendez8@email.com,,57
084202442,Shaylynn,Scorthorne,sscorn2@email.com,,78
275079882,Madonna,Willatt,mwillatt2@email.com,,31
071001041,Bancroft,Padfield,bpadfield1@email.com,,47
261170740,Rici,Everard,reverarda0@email.com,,51
113105478,Lishe,Dashkovich,ldashkovich8@email.com,,56
267089712,Alexandrina,Abate,aabate6@email.com,,56
041002203,Jordon,Ribbens,jribbens3@email.com,,36
021308176,Jennette,Andrassy,jandrassye6@email.com,,57
122239937,Hamid,Chapell,hchapell7@email.com,,64
021109935,Cordy,Crosetto,ccrosettog5@email.com,,43
111026041,Tiphanie,Gwin,tgwin1@email.com,,56
072408708,Leanor,Izachik,lizachik8@email.com,,62
221370030,Lissy,Tuffley,ltuffley0@email.com,,70
104900048,Myrta,Mathieson,mmathieson8@email.com,,47
111311413,Cynthea,Fowles,cfowles3@email.com,,55
091408695,Zacherie,Branch,zbranch5@email.com,,34
231372183,Britney,Blackesland,bblackesland3@email.com,,60
263190634,Theda,Menco,tmenco4@email.com,,16
021606580,Carin,Schrader,cschrader0@email.com,,49
074902341,Shawn,Moston,smoston1@email.com,,57
107006088,Virge,Sinnat,vsinnat8@email.com,,47
091807254,Alano,Jotcham,ajotcham4@email.com,,66
011601029,Pietra,Roy,proy9@email.com,,60
122240010,Orren,Danihelka,odanihelka0@email.com,,39
091400046,Angie,Grindell,agrindell6@email.com,,56
071001630,Vachel,Swancock,vswancock0@email.com,,42
061020977,Zane,Bellie,zbellie7@email.com,,74
065403150,Delphine,Sirette,dsirette0@email.com,,55
081211300,Oliver,Pynner,opynner0@email.com,,68
011601074,Barbra,Antyukhin,bantyukhin4@email.com,,62
091014898,Charmain,Elce,celce8@email.com,,58
042100049,Herold,Klawi,hklawi9@email.com,,32
091906359,Mariann,Mousdall,mmousdall9@email.com,,41
101114992,Horatius,Romera,hromera2@email.com,,48
031307604,Dall,Laboune,dlaboune4@email.com,,54
111308031,Teddie,Carlett,tcarlett1@email.com,,70
114919676,Kelley,Klimentyonok,kklimentyonok6@email.com,,57
322283835,Colene,Corgenvin,ccorgenvin5@email.com,,40
051402071,Diannne,Pashan,dpashan1@email.com,,71
081904808,Staffard,Cullerne,scullerne8@email.com,,38
074914407,Alwin,Hartzog,ahartzog7@email.com,,45
103110813,Johnny,Calbreath,jcalbreath3@email.com,,18
241282632,Sophronia,Fere,sfere2@email.com,,56
063106734,Timothea,Lambird,tlambird4@email.com,,73
064102290,Lauralee,Mc Caghan,lmccaghan0@email.com,,64
075017947,Denny,Dani,ddani7@email.com,,38
072414297,Marilin,Lilloe,mlilloe7@email.com,,48
083908420,Hephzibah,Mizzi,hmizzi0@email.com,,50
075902340,Jourdan,Toulamain,jtoulamain0@email.com,,64
114901147,Natassia,Daniele,ndaniele7@email.com,,42
061000256,Kellina,Newlands,knewlands6@email.com,,43
051009296,Andria,Thurske,athurske6@email.com,,36
084205656,Shanie,Marczyk,smarczyk6@email.com,,42
041211298,Norine,Spinella,nspinella8@email.com,,40
071922379,Rudiger,Cornbell,rcornbell9@email.com,,41
092101030,Reynold,Dumbelton,rdumbelton0@email.com,,51
265270413,Fielding,Gouldstraw,fgouldstraw3@email.com,,45
051502434,Toni,Wong,twong4@email.com,,50
072413557,Daisey,Shireff,dshireff7@email.com,,60
231371799,Cristin,Albutt,calbutt9@email.com,,53
122203248,Peterus,Ojeda,pojeda8@email.com,,47
084205766,Nissie,Winterflood,nwinterflood6@email.com,,47
075900465,Franciska,Meatyard,fmeatyard5@email.com,,68
051502434,Tyler,Alekhov,talekhov4@email.com,,38
114922430,Cordy,Byllam,cbyllam0@email.com,,72
221982156,Gabriel,Limrick,glimrick6@email.com,,71
084201223,Nonie,McGaffey,nmcgaffey3@email.com,,35
071909211,Kittie,Alman,kalman1@email.com,,48
071900663,Gran,Smithies,gsmithies3@email.com,,49
067006432,Sammy,Gundry,sgundry2@email.com,,36
081503490,Ozzy,Iskowitz,oiskowitz0@email.com,,64
081307227,Charlie,Waldram,cwaldram7@email.com,,72
211272465,Benn,Adnams,bandnams5@email.com,,30
065404913,Fidelia,Katt,fkatt3@email.com,,42
082900380,Fidelia,Jahndel,fjahndel0@email.com,,35
061101100,Marietta,Bourgourd,mbourgourd0@email.com,,51
075072157,Elberta,Argyle,eargyle7@email.com,,49
082900911,Dru,Hendrickson,dhendrickson1@email.com,,70
301271790,Clemmie,Annett,cannett0@email.com,,40
122243321,Even,Stebbings,estebbings1@email.com,,57
112204286,Eduardo,Scholz,escholz6@email.com,,23
064108605,Ruggiero,Colrein,rcolrein5@email.com,,70
256072701,Lesli,Tolefree,ltolefree1@email.com,,52
091201818,Caitrin,Bogeys,cbogeys8@email.com,,51
063115437,Rodrick,Bisset,rbisset7@email.com,,32
211384162,Ladonna,Bridgewood,lbridgewood2@email.com,,23
122238938,Westleigh,Trevear,wtrevear8@email.com,,46
111101377,Allen,Petrovsky,apetrovsky7@email.com,,59
051404118,Tabbitha,Havers,thavers8@email.com,,39
262285223,Chev,Helstrip,chelstrip3@email.com,,48
072413599,Garvey,Done,gdone9@email.com,,22
075000734,Kalila,Wondraschek,kwondraschek4@email.com,,44
065002030,Eustace,Hourican,ehourican0@email.com,,79
084307033,Frayda,Drewell,fdrewell3@email.com,,22
114912275,Corena,Seyler,cseyler5@email.com,,36
071925538,Aurie,Campanelli,acampanelli8@email.com,,56
263190757,Juanita,Ruhben,jruhben7@email.com,,56
082905181,Danica,Hillam,dhillam1@email.com,,67
211170169,Carney,Bastick,cbastick9@email.com,,44
111304608,Rori,Wragge,rwragge8@email.com,,49
101918240,Rebbecca,Holywell,rholywell0@email.com,,56
091300719,Carina,Manuello,cmanuello9@email.com,,63
053202305,Mandi,Meas,mmeas5@email.com,,61
071923747,Audry,Warkup,awarkup7@email.com,,72
071001630,Meryl,Mackett,mmackett0@email.com,,38
053201186,Richart,Tolliday,rtolliday6@email.com,,49
071105002,Alejoa,Kinkead,akinkead2@email.com,,53
065203499,Minna,Dunkley,mdunkley9@email.com,,49
102101496,Alyse,Gabits-agabits6@email.com,,54
062000019,Orsa,Learmouth,olearmouth9@email.com,,40
042201948,Renata,Dykas,rdykas8@email.com,,37
062206415,Rogers,Ivanyushin,rivanyushin5@email.com,,58
122243457,Shelton,Kunc,skunc7@email.com,,40
061204612,Arlen,Gobeau,agobeau2@email.com,,42
113110780,Fidel,Rodgers,frodgers0@email.com,,59
113122655,Peadar,Brompton,pbrompton5@email.com,,41
103101929,Noel,Caught,ncaught9@email.com,,32
061209756,Mitchell,Spickett,mspickett6@email.com,,51
082901457,Carney,Redsull,credsull7@email.com,,53
211970055,Nelia,Mattke,nmattke5@email.com,,41
065002108,Marcellus,Discombe,mdiscombe8@email.com,,49
323270300,Polly,Savine,psavine0@email.com,,26
067005679,Nobe,Brake,nbrake9@email.com,,37
073915520,Nollie,Locke,nlocke0@email.com,,34
067091719,Dino,Davenhall,ddavenhall9@email.com,,72
063113015,Frants,Boughey,fboughey5@email.com,,63
104900679,Frannie,Rigg,frigg9@email.com,,65
111916452,Lazaro,Kid,lkid2@email.com,,52
111906161,Berny,Caps,bcaps1@email.com,,29
264279334,Gerhard,McNellis,gmcnellis4@email.com,,59
101106379,Demetra,Cristofor,dcristofor9@email.com,,81
113110816,Timmie,Mitcham,tmitcham6@email.com,,64
064108757,Holmes,Dunwoody,hdunwoody7@email.com,,46
107003418,Sully,Dawid,sdawid8@email.com,,32
073903150,Myer,Mandre,mmandre0@email.com,,47
211372352,Marco,Drysdale,mdrysdale2@email.com,,38
103112329,Tabina,Cardinale,tcardinale9@email.com,,51
092901803,Adams,Baythorp,abaythorp3@email.com,,20
122105252,Jerrold,Bailess,jbailess2@email.com,,70
284272654,Colman,Cavil,ccavil4@email.com,,57
111900455,Caryl,Audsley,caudsley5@email.com,,40
021207358,Cindee,Tomik,ctomik8@email.com,,51
083002533,Isis,Dudley,idudley3@email.com,,38
261170740,Caldwell,Izzard,cizzard0@email.com,,51
101102496,Olimpia,Maymand,omaymand6@email.com,,50
064203021,Rhiamon,Glowach,rglowach1@email.com,,58
021172661,Beale,Kordova,bkordova1@email.com,,34
122041523,Bebe,Crippen,bcrippen3@email.com,,50
065301689,Kippy,Joselin,kjoselin9@email.com,,67
111924305,Derron,Mothersdale,dmothersdale5@email.com,,49
074000162,Early,Brilon,ebrilon2@email.com,,49
322271779,Vitoria,Baxstar,vbaxstar9@email.com,,34
114922430,Giulietta,Finnie,gfinnie0@email.com,,43
055000770,Cosmo,Harriot,charriot0@email.com,,49
063114632,Netta,Glazyer,nglazyer2@email.com,,54
101102836,Ruggiero,Spelsbury,rspelsbury6@email.com,,63
103101424,Sunny,Vogeler,svogeler4@email.com,,51
111321270,Gustie,Roelvink,groelvink0@email.com,,49
073920845,Marilin,Caldow,mcaldow5@email.com,,44
031317380,Lindsay,Slisby,lslisby0@email.com,,46
065301197,Lily,Jowitt,ljowitt7@email.com,,58
071909198,Lezlie,Trotton,ltrotton8@email.com,,54
043000122,Lyndsie,Flaune,lflaune2@email.com,,52
211272504,Riane,Gilford,rgilford4@email.com,,42
091401919,Tami,Khotler,tkhotler9@email.com,,36
064108757,Marrissa,Alywin,malywin7@email.com,,57
071902629,Gav,Maile,gmaile9@email.com,,45
031301053,Ben,Stockport,bstockport3@email.com,,31
113014077,Fran,Eyckelbeck,feyckelbeck7@email.com,,59
065303069,Ruperto,Asp,rasp9@email.com,,33
071917232,Shanon,Polly,spolly2@email.com,,61
086518723,Noak,Pickring,npickring3@email.com,,32
101100922,Blinni,Marsie,bmarsie2@email.com,,48
111306871,Demetri,Plackstone,dplackstone1@email.com,,48
052100929,Fidole,Gremane,fgremane9@email.com,,47
091015224,Magdalene,Bushell,mbushell4@email.com,,58
071114491,Matthus,Cecely,mcecely1@email.com,,50
031000040,Isac,Itzhaiek,iitzhaiek0@email.com,,46
103912723,Sawyer,Kaman,skaman3@email.com,,25
281581047,Berti,Finch,bfinch7@email.com,,59
064000185,Jeff,Brogiotti,jbrogiotti5@email.com,,47
091916093,Elissa,Peaden,epheaden3@email.com,,68
041201703,Skipp,Leet,sleet3@email.com,,79
243073632,Otha,Lynskey,olynskey2@email.com,,51
113001077,Staford,Darell,sdarell7@email.com,,50
103106843,Tony,Cole,tcole3@email.com,,49
221472776,Kleon,Caskie,kcaskie6@email.com,,45
075901642,Mathilde,Lembrick,mlembrick2@email.com,,61
104000702,Lita,McCroft,lmcroft2@email.com,,24
226072375,Terrijo,Tersay,ttersay5@email.com,,60
253173661,Janot,Wittleton,jwittleton1@email.com,,49
264271361,Dalis,Marrable,dmarrable1@email.com,,39
075907497,Elisabeth,Girsch,egirsch7@email.com,,78
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
