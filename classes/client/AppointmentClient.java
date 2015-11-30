package client;


import server.AppointmentService;

public class AppointmentClient {

    public static void main(String[] argv){

         AppointmentService service = new AppointmentService();
         AppointmentService port = service;

        try{
            System.out.println("Initialized::" + port.initialize());
            System.out.println("\n\nAll Appointments::\n" + port.getAllAppointments());
            System.out.println("\n\nGet One Appointment (790)::\n" + port.getAppointment("790"));

            String xmlStyle = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>" +
                    "<appointment><date>2016-12-30</date> <time>11:30</time><patientId>220</patientId>" +
                    "<physicianId>20</physicianId><pscId>520</pscId><phlebotomistId>110</phlebotomistId>" +
                    "<labTests><test id=\"86900\" dxcode=\"292.9\" /><test id=\"86609\" dxcode=\"307.3\" />" +
                    "</labTests></appointment>";
            System.out.println("\n\nGood Appointment::\n" + port.addAppointment(xmlStyle));

            xmlStyle = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>" +
                    "<appointment><date>2016-12-30</date> <time>11:30</time><patientId>230</patientId>" +
                    "<physicianId>20</physicianId><pscId>510</pscId><phlebotomistId>110</phlebotomistId>" +
                    "<labTests><test id=\"86900\" dxcode=\"292.9\" /><test id=\"86609\" dxcode=\"307.3\" />" +
                    "</labTests></appointment>";
            System.out.println("\n\nDuplicate Appointment::\n" + port.addAppointment(xmlStyle));

            xmlStyle = "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"no\"?><appointment>" +
                    "<date>2016-13-34</date><time>99:00</time><patientId>bad</patientId>" +
                    "<physicianId>bad</physicianId> <pscId>bad</pscId><phlebotomistId>bad</phlebotomistId>" +
                    "<labTests><test id=\"bad\" dxcode=\"000\" /></labTests></appointment>";
            System.out.println("\n\nBad Appointment::\n" + port.addAppointment(xmlStyle));
        }catch(Exception e){}//catch
    }
}
