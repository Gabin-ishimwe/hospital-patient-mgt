package com.hospitalMgt.patiencemgt.services;

import com.hospitalMgt.patiencemgt.entities.Patient;
import com.hospitalMgt.patiencemgt.exceptions.NotFoundException;
import com.hospitalMgt.patiencemgt.repositories.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;


    public Patient createProfileDoc(Patient patient) {
        return patientRepository.save(patient);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient findOnePatient(String email) throws NotFoundException {
        Patient patient = patientRepository.findByEmail(email);
        if(patient == null) throw new NotFoundException("Patient not found");
        return patient;
    }

    public Patient updateProfile(String email, Patient patient) throws NotFoundException {
        Patient findPatient = patientRepository.findByEmail(email);
        if(patient == null) throw new NotFoundException("Patient not found");
        return patientRepository.save(
                  Patient.builder()
                          .firstName(patient.getFirstName())
                          .lastName(patient.getLastName())
                          .gender(patient.getGender())
                          .medicalHistory(patient.getMedicalHistory())
                          .contactNumber(patient.getContactNumber())
                          .reasonOfVisit(patient.getReasonOfVisit())
                          .symptoms(patient.getSymptoms())
                          .insuranceProvider(patient.getInsuranceProvider())
                          .build()
        );
    }
}
