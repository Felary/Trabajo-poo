package Ventanas;

import Modelado.Administracion;
import com.google.gson.Gson;
import Modelado.Persona;
import Modelado.Apartamento;
import Modelado.Casa;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class NuevoPropietario extends javax.swing.JFrame {

    /**
     * Creates new form PagarAdministracion
     */
    public NuevoPropietario() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        txtIdApartamento.setEditable(false);
        txtTorreApartamento.setEditable(false);
        txtIdCasa.setEditable(false);
    }

    // <editor-fold defaultstate="collapsed" desc="FUNCIONES">
    // <editor-fold defaultstate="collapsed" desc="FUNCIONES PERSONA">
    private Persona guardarDatosPersona() {
        String nombre = txtNombreCompleto.getText();
        String telefono = txtTelefono.getText();
        String identificacion = txtIdentificacion.getText();
        double sueldo = Double.parseDouble(txtSueldo.getText());
        Persona nuevaPersona = new Persona(nombre, identificacion, telefono, sueldo);
        return nuevaPersona;
    }

    private void registrarPersonas() {        
        Gson documento = new Gson();
        String fichero = "";
        ArrayList<Persona> ListaPersonas = new ArrayList<>();
        boolean personaEncontrada;
        Persona nuevaPersona = guardarDatosPersona();
        String identificacion = txtIdentificacion.getText();

        try {
            BufferedReader br = new BufferedReader(new FileReader("Personas.json"));
            String linea;
            while ((linea = br.readLine()) != null) {
                fichero += linea;
            }
            if (fichero.isEmpty()) {
                ListaPersonas.add(nuevaPersona);

                String datosPersona = documento.toJson(ListaPersonas);

                try (BufferedWriter bw = new BufferedWriter(new FileWriter("Personas.json"))) {
                    
                    bw.write(datosPersona);
                    opcionApartamentoCasa();
                    JOptionPane.showMessageDialog(null, "Nuevo Propietario Agregado", "Felicidades", 1);

                    Menu abrir = new Menu();
                    abrir.setVisible(true);
                    this.setVisible(false);

                } catch (IOException ex) {
                    Logger.getLogger(Persona.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                try {
                    BufferedReader br2 = new BufferedReader(new FileReader("Personas.json"));
                    ListaPersonas = new Gson().fromJson(br2, new TypeToken<ArrayList<Persona>>() {
                    }.getType());
                    personaEncontrada = valirdarIdPersona(identificacion, ListaPersonas);

                    if (personaEncontrada) {
                        JOptionPane.showMessageDialog(null, "Un propietario ya ha sido registrado con esa misma identificacion", "ERROR", 0);
                        limpiarCampos();
                    } else {

                        ListaPersonas.add(nuevaPersona);
                        String datosPersona = documento.toJson(ListaPersonas);

                        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Personas.json"))) {
                            bw.write(datosPersona);
                            opcionApartamentoCasa();
                            JOptionPane.showMessageDialog(null, "Nuevo Propietario Agregado", "Felicidades", 1);
                            Menu abrir = new Menu();
                            abrir.setVisible(true);
                            this.setVisible(false);

                        } catch (IOException ex) {
                            Logger.getLogger(Persona.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        br2.close();
                    }
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error Fichero no encontrado", 0);

                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", 0);
                }
            }

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error Fichero no encontrado", 0);

        } catch (IOException ex) {

            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", 0);
        }
    }

    private boolean valirdarIdPersona(String id, ArrayList<Persona> personasRegistradas) {

        long numeroPersonasRepetidas = personasRegistradas.stream().filter(persona -> id.equals(persona.getId())).count();

        if (numeroPersonasRepetidas == 0) {
            return false;
        }

        return true;

    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="FUNCIONES APARTAMENTO">
    private ArrayList obtenerListaPropietariosApartamentos() {
        ArrayList<Apartamento> listaPropietariosApartamentos;

        try (BufferedReader br = new BufferedReader(new FileReader("Propietarios_Apartamentos.json"))) {

            listaPropietariosApartamentos = new Gson().fromJson(br, new TypeToken<ArrayList<Apartamento>>() {
            }.getType());

            br.close();
            return listaPropietariosApartamentos;
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error Fichero no encontrado", 0);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", 0);
        }
        return null;
    }   //retorna los datos de los apartamentos.

    private void registrarPropietarioApartamento() {
        Gson documento = new Gson();
        String fichero = "";
        Apartamento propietarioNuevoApartamento = guardarPropietarioApartamento();
        ArrayList<Apartamento> listaPropietariosApartamentos = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("Propietarios_Apartamentos.json"));
            String linea;
            while ((linea = br.readLine()) != null) {
                fichero += linea;
            }
            if (fichero.isEmpty()) {

                listaPropietariosApartamentos.add(propietarioNuevoApartamento);
                String datosPropietarioApartamento = documento.toJson(listaPropietariosApartamentos);

                try (BufferedWriter bw = new BufferedWriter(new FileWriter("Propietarios_Apartamentos.json"))) {
                    bw.write(datosPropietarioApartamento);
                    modificarNumeroDisponibleApartamentos();

                } catch (IOException ex) {
                    Logger.getLogger(Apartamento.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                try {
                    BufferedReader br2 = new BufferedReader(new FileReader("Propietarios_Apartamentos.json"));
                    listaPropietariosApartamentos = new Gson().fromJson(br2, new TypeToken<ArrayList<Apartamento>>() {
                    }.getType());

                    listaPropietariosApartamentos.add(propietarioNuevoApartamento);
                    String datosPropietarioApartamento = documento.toJson(listaPropietariosApartamentos);

                    try (BufferedWriter bw = new BufferedWriter(new FileWriter("Propietarios_Apartamentos.json"))) {
                        bw.write(datosPropietarioApartamento);
                        modificarNumeroDisponibleApartamentos();

                    } catch (IOException ex) {
                        Logger.getLogger(Apartamento.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    br2.close();

                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error Fichero no encontrado", 0);

                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", 0);
                }
            }

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error Fichero no encontrado", 0);

        } catch (IOException ex) {

            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", 0);
        }
    }   //guarga un nuevo propietario

    private boolean validarIdApartamento(String id, ArrayList<Apartamento> listaApartamentos, ArrayList<Administracion> edificaciones) {

        if (edificaciones.get(0).getNumeroApartamentos() > 0) {

            long disponibilidad = listaApartamentos.stream().filter(apartamento -> id.equals(apartamento.getIdApartamento())).count();

            if (disponibilidad == 0) {
                return false;
            }
        }
        return true;

    }   //Valida si hay apartamentos disponibles.

    

    private Apartamento guardarPropietarioApartamento() {

        ArrayList<Apartamento> listaPropietariosApartamentos = obtenerListaPropietariosApartamentos();
        ArrayList<Administracion> listaEdificaciones = mostrarDatosConjunto();
        Persona datosPropietario = guardarDatosPersona();
        boolean apartamentoLibre, validarNumeroIdApartamento;

        validarNumeroIdApartamento = validarSoloNumeros(txtIdApartamento.getText().trim());
        String idApartamento = txtIdApartamento.getText();
        String torre = txtTorreApartamento.getText().toUpperCase();
        Boolean estado = false;

        int numeroApartamentos = listaEdificaciones.get(0).getNumeroApartamentos();
        int numeroCasas = listaEdificaciones.get(0).getNumeroCasas();
        double precioAdminApartamentos = listaEdificaciones.get(0).getPrecioAdministracionApartamentos();
        double precioAdminCasas = listaEdificaciones.get(0).getPrecioAdministracionCasas();

        if (validarNumeroIdApartamento) {

            if (listaPropietariosApartamentos == null) {

                Apartamento propietarioNuevoApartamento = new Apartamento(idApartamento, torre, numeroCasas,
                        numeroApartamentos, precioAdminApartamentos, precioAdminCasas, datosPropietario, estado);
                return propietarioNuevoApartamento;
            } else {
                apartamentoLibre = validarIdApartamento(idApartamento, listaPropietariosApartamentos, listaEdificaciones);

                if (!apartamentoLibre) {
                    Apartamento propietarioNuevoApartamento = new Apartamento(idApartamento, torre, numeroCasas,
                            numeroApartamentos, precioAdminApartamentos, precioAdminCasas, datosPropietario, estado);

                    return propietarioNuevoApartamento;
                }
                JOptionPane.showConfirmDialog(null, "EL apartamento no esta disponible", "PRECAUCION", 0);
                limpiarCampos();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Solo se pueden ingresar valores numericos en el numero de apartamento", "ERROR", 0);
            limpiarCampos();
        }
        return null;
    }  //Guarda los datos de una persona y del apartamento en el obj.

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="FUNCIONES CASA">
    private ArrayList obtenerListaPropietariosCasas() {
        ArrayList<Casa> listaPropietariosCasa = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("Propietarios_Casas.json"))) {

            listaPropietariosCasa = new Gson().fromJson(br, new TypeToken<ArrayList<Casa>>() {
            }.getType());

            br.close();
            return listaPropietariosCasa;
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error Fichero no encontrado", 0);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", 0);
        }
        return listaPropietariosCasa;
    }

    private boolean validarIdCasa(String id, ArrayList<Casa> listaCasas, ArrayList<Administracion> edificaciones) {

        if (edificaciones.get(0).getNumeroApartamentos() > 0) {

            long disponibilidad = listaCasas.stream().filter(casa -> id.equals(casa.getIdCasa())).count();

            if (disponibilidad == 0) {
                return false;
            }
        }
        return true;

    }

    private Casa guardarPropietarioCasa() {
        ArrayList<Casa> listaPropietariosCasa = obtenerListaPropietariosCasas();
        ArrayList<Administracion> listaEdificaciones = mostrarDatosConjunto();
        Persona datosPropietario = guardarDatosPersona();
        boolean casaLibre, validarNumeroIdCasa;

        validarNumeroIdCasa = validarSoloNumeros(txtIdCasa.getText().trim());
        String idCasa = txtIdCasa.getText();
        Boolean estado = false;

        int numeroApartamentos = listaEdificaciones.get(0).getNumeroApartamentos();
        int numeroCasas = listaEdificaciones.get(0).getNumeroCasas();
        double precioAdminApartamentos = listaEdificaciones.get(0).getPrecioAdministracionApartamentos();
        double precioAdminCasas = listaEdificaciones.get(0).getPrecioAdministracionCasas();

        if (validarNumeroIdCasa) {

            if (listaPropietariosCasa == null) {

                Casa propietarioNuevoCasa = new Casa(idCasa, numeroCasas, numeroApartamentos, precioAdminApartamentos, precioAdminCasas, datosPropietario, estado);
                return propietarioNuevoCasa;
            } else {
                casaLibre = validarIdCasa(idCasa, listaPropietariosCasa, listaEdificaciones);

                if (!casaLibre) {
                    Casa propietarioNuevoCasa = new Casa(idCasa, numeroCasas, numeroApartamentos, precioAdminApartamentos, precioAdminCasas, datosPropietario, estado);
                    return propietarioNuevoCasa;
                }
                JOptionPane.showConfirmDialog(null, "La casa no esta disponible", "PRECAUCION", 0);
                limpiarCampos();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Solo se pueden ingresar valores numericos en el numero de casa", "ERROR", 0);
            limpiarCampos();
        }
        return null;

    }

    private void registrarPropietarioCasa() {
        Gson documento = new Gson();
        String fichero = "";
        Casa propietarioNuevoCasa = guardarPropietarioCasa();
        ArrayList<Casa> listaPropietariosCasas = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("Propietarios_Casas.json"));
            String linea;
            while ((linea = br.readLine()) != null) {
                fichero += linea;
            }
            if (fichero.isEmpty()) {

                listaPropietariosCasas.add(propietarioNuevoCasa);
                String datosPropietarioCasa = documento.toJson(listaPropietariosCasas);

                try (BufferedWriter bw = new BufferedWriter(new FileWriter("Propietarios_Casas.json"))) {
                    bw.write(datosPropietarioCasa);
                    modificarNumeroDisponibleCasas();

                } catch (IOException ex) {
                    Logger.getLogger(Apartamento.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                try {
                    BufferedReader br2 = new BufferedReader(new FileReader("Propietarios_Casas.json"));
                    listaPropietariosCasas = new Gson().fromJson(br2, new TypeToken<ArrayList<Casa>>() {
                    }.getType());

                    listaPropietariosCasas.add(propietarioNuevoCasa);
                    String datosPropietarioCasa = documento.toJson(listaPropietariosCasas);

                    try (BufferedWriter bw = new BufferedWriter(new FileWriter("Propietarios_Casas.json"))) {
                        bw.write(datosPropietarioCasa);
                        modificarNumeroDisponibleCasas();

                    } catch (IOException ex) {
                        Logger.getLogger(Casa.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    br2.close();

                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error Fichero no encontrado", 0);

                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", 0);
                }
            }

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error Fichero no encontrado", 0);

        } catch (IOException ex) {

            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", 0);
        }
    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="FUNCIONES CONJUNTO">
    private void modificarNumeroDisponibleApartamentos() {
        Gson documento = new Gson();
        ArrayList<Administracion> edificaciones = mostrarDatosConjunto();
        int apartamentos = edificaciones.get(0).getNumeroApartamentos();
        int apartamentosDisponibles = apartamentos - 1;
        edificaciones.get(0).setNumeroApartamentos(apartamentosDisponibles);
        String datosAdministracion = documento.toJson(edificaciones);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Administracion.json"))) {
            bw.write(datosAdministracion);

        } catch (IOException ex) {
            Logger.getLogger(Administracion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void modificarNumeroDisponibleCasas() {
        Gson documento = new Gson();
        ArrayList<Administracion> edificaciones = mostrarDatosConjunto();
        int casas = edificaciones.get(0).getNumeroCasas();
        int casasDisponibles = casas - 1;
        edificaciones.get(0).setNumeroCasas(casasDisponibles);
        String datosAdministracion = documento.toJson(edificaciones);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Administracion.json"))) {
            bw.write(datosAdministracion);

        } catch (IOException ex) {
            Logger.getLogger(Administracion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private ArrayList mostrarDatosConjunto() {

        ArrayList<Administracion> ListaEdificaciones;

        try {
            BufferedReader br = new BufferedReader(new FileReader("Administracion.json"));
            ListaEdificaciones = new Gson().fromJson(br, new TypeToken<ArrayList<Administracion>>() {
            }.getType());

            br.close();

            return ListaEdificaciones;

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error Fichero no encontrado", 0);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", 0);
        }
        return null;
    }   //Retorna los datos guardados en edificacion.

    // </editor-fold> 
    
    private void limpiarCampos(){
        txtIdApartamento.setText("");
        txtIdCasa.setText("");
        txtIdentificacion.setText("");
        txtNombreCompleto.setText("");
        txtSueldo.setText("");
        txtTelefono.setText("");
        txtTorreApartamento.setText("");       
    }
    
    private boolean validarSoloNumeros(String id) {

        return id.matches("[0-9]");
    }
    private void opcionApartamentoCasa() {
        if (boxApartamento.isSelected()) {
            registrarPropietarioApartamento();
        } else if (boxCasa.isSelected()) {
            registrarPropietarioCasa();
        }
    }   //valida la ruta de guardado apartamento / casa.
    // </editor-fold> 

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        opcionCasaApartamento = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtIdentificacion = new javax.swing.JTextField();
        botonGuardarDatos = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        botonVolver = new javax.swing.JButton();
        txtNombreCompleto = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtSueldo = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        boxApartamento = new javax.swing.JCheckBox();
        boxCasa = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtIdApartamento = new javax.swing.JTextField();
        txtIdCasa = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtTorreApartamento = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("DATOS NUEVO PROPIETARIO");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("NOMBRE COMPLETO");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("IDENTIFICACION");

        botonGuardarDatos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        botonGuardarDatos.setText("Guardar Datos");
        botonGuardarDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardarDatosActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("TELEFONO");

        botonVolver.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        botonVolver.setText("Volver");
        botonVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonVolverActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("SUELDO");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Apartamento");

        opcionCasaApartamento.add(boxApartamento);
        boxApartamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxApartamentoActionPerformed(evt);
            }
        });
        boxApartamento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                boxApartamentoKeyReleased(evt);
            }
        });

        opcionCasaApartamento.add(boxCasa);
        boxCasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxCasaActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Casa");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Numero del apartamento");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Numero de la casa");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Torre");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTorreApartamento, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(boxCasa)
                        .addComponent(boxApartamento)
                        .addComponent(txtIdApartamento, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                        .addComponent(txtIdCasa)))
                .addContainerGap(117, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(boxApartamento)
                    .addComponent(jLabel3))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtIdApartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtTorreApartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(boxCasa))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtIdCasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(134, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(163, 163, 163))
            .addGroup(layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(botonGuardarDatos)
                        .addGap(77, 77, 77)
                        .addComponent(botonVolver))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(77, 77, 77)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtIdentificacion)
                            .addComponent(txtTelefono)
                            .addComponent(txtNombreCompleto, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                            .addComponent(txtSueldo)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel1)
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombreCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtSueldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonGuardarDatos)
                    .addComponent(botonVolver))
                .addGap(50, 50, 50))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonGuardarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarDatosActionPerformed
        registrarPersonas();

    }//GEN-LAST:event_botonGuardarDatosActionPerformed

    private void botonVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonVolverActionPerformed
        Menu abrir = new Menu();
        abrir.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_botonVolverActionPerformed

    private void boxApartamentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_boxApartamentoKeyReleased

    }//GEN-LAST:event_boxApartamentoKeyReleased

    private void boxApartamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxApartamentoActionPerformed
        txtIdApartamento.setEditable(true);
        txtTorreApartamento.setEditable(true);
    }//GEN-LAST:event_boxApartamentoActionPerformed

    private void boxCasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxCasaActionPerformed
        txtIdCasa.setEditable(true);
    }//GEN-LAST:event_boxCasaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NuevoPropietario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NuevoPropietario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NuevoPropietario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NuevoPropietario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NuevoPropietario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonGuardarDatos;
    private javax.swing.JButton botonVolver;
    private javax.swing.JCheckBox boxApartamento;
    private javax.swing.JCheckBox boxCasa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.ButtonGroup opcionCasaApartamento;
    private javax.swing.JTextField txtIdApartamento;
    private javax.swing.JTextField txtIdCasa;
    private javax.swing.JTextField txtIdentificacion;
    private javax.swing.JTextField txtNombreCompleto;
    private javax.swing.JTextField txtSueldo;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTorreApartamento;
    // End of variables declaration//GEN-END:variables
}
