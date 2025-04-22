    package com.e_civil.e_civil.controllers;

    import com.e_civil.e_civil.models.Pv;
    import com.e_civil.e_civil.services.PvService;
    import lombok.AllArgsConstructor;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;
    import java.util.Optional;

    @RestController
    @RequestMapping("api/pvs")
    @CrossOrigin("*")
    @AllArgsConstructor
    public class PvController {
        private PvService pvService;

        @GetMapping
        public List<Pv> getAllPvs(){
            return pvService.findAll();
        }

        @GetMapping("{id}")
        public Optional<Pv> findPvById(Long id){
            return pvService.findById(id);
        }

        @GetMapping("/policier/{id}")
        public ResponseEntity<List<Pv>> getPvsByPolicier(@PathVariable Long id) {
            List<Pv> pvs = pvService.getPvsByPolicier(id);
            return ResponseEntity.ok(pvs);
        }


        @PostMapping()
        public Pv createPv(@RequestBody Pv pv){
            if (pv.getId() != null) {
                System.out.println("Attention : création avec ID = " + pv.getId());
            }
            return pvService.create(pv);
        }

        @PatchMapping("{id}")
        public Pv updatePv(@PathVariable Long id, @RequestBody Pv pv){
            return pvService.update(pv,id);
        }

        @DeleteMapping("{id}")
        public void deletePv(@PathVariable Long id){
            pvService.delete(id);
        }
    }
