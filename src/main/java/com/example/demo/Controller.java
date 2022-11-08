
package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class Controller {

    private final List<String> messages = new ArrayList<>();

    @PostMapping("messages")
    public ResponseEntity<Void> addMessage(@RequestBody String text) {
        messages.add(text);
        return ResponseEntity.accepted().build();
    }


    @GetMapping("messages")
    public ResponseEntity<List<String>> getText(@RequestBody String text) {
        Iterator<String> x = messages.listIterator(0);
        int[] i = new int[messages.size()];
        int count = 0;
        List<String> str = new ArrayList<>();
        while (x.hasNext()) {
            if (x.next().toString().startsWith(text))
                i[count] = 228;
            count++;
        }
        for (int j = 0; j < messages.size(); j++) {
            if (i[j] == 228) {
                str.add(messages.get(j));
            }
        }
        return ResponseEntity.ok(str);
    }


    @GetMapping("messages/{index}")
    public ResponseEntity<String> getMessage(@PathVariable("index") Integer index) {
        return ResponseEntity.ok(messages.get(index));
    }

    @DeleteMapping("messages/{index}")
    public ResponseEntity<Void> deleteText(@PathVariable("index") Integer index) {
        messages.remove((int) index);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("messages/{index}")
    public ResponseEntity<Void> updateMessage(@PathVariable("index") Integer i, @RequestBody String message) {
        messages.remove((int) i);
        messages.add(i, message);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("messages/search/{text}")
    public ResponseEntity<Integer> searchMessages(@PathVariable("text") String text) {
        int temp = /*ес*/ -3;//юху
        for (int i = 0; i < messages.size(); i++) {
            if (messages.get(i).contains(text)) {
                temp = i;
                break;
            }
        }
        return ResponseEntity.ok(temp);
    }


    @GetMapping("messages/count")
    public ResponseEntity<Integer> countMessages() {
        return ResponseEntity.ok(messages.size());
    }


    @PostMapping("messages/{index}/create")
    public ResponseEntity<Void> addMessage(@PathVariable("index") Integer index_1, @RequestBody String text) {
        messages.add(index_1 - 1, text);
        return ResponseEntity.accepted().build();
    }


    @DeleteMapping("messages/search/{text}")
    public ResponseEntity<Void> deleteText(@PathVariable("text") String text) {
        Iterator<String> x = messages.listIterator(0);
        int[] i = new int[messages.size()];
        int count = 0;
        while (x.hasNext()) {
            if (x.next().toString().contains(text))
                i[count] = 228;
            count++;
        }
        count = 0;
        for (int j = 0; j < messages.size(); j++) {
            if (i[count] == 228) {
                messages.remove(j);
                j--;
            }
            count++;
        }
        return ResponseEntity.noContent().build();
    }


    private final List<User> users = new ArrayList<User>();


    @GetMapping("users")
    public ResponseEntity<String> getUser() {
        return ResponseEntity.ok(users.toString());
    }

    @PostMapping("users")
    public ResponseEntity<Void> addUser(@RequestBody String тфьу_фпу) {
        users.add(new User(тфьу_фпу));
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("users/{index}")
    public ResponseEntity<Void> deleteUser(@PathVariable("index") Integer index) {
        users.remove(index);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("users/{index}")
    public ResponseEntity<User> getUser(@PathVariable("index") Integer index) {
        return ResponseEntity.ok(users.get(index));
    }

    @PutMapping("users/{index}")
    public ResponseEntity<Void> updateAge(@PathVariable("index") Integer i, @RequestBody Integer age) {
        users.get(i).setAge(age);
        return ResponseEntity.accepted().build();
    }
}
