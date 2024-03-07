/*SQL requests*/
INSERT INTO contacts (name, phone)
VALUES (:name, :phone)
SELECT * FROM contacts
SELECT * FROM contacts WHERE id = :id
UPDATE contacts SET name = :name,
                    phone = :phone WHERE id = :id
DELETE FROM contacts WHERE id = :id

/*REST requests*/
POST /contacts
Content-Type: application/json
{
  "id": 1,
  "name": "Pavel",
  "phone": "+380677889910"
}

GET /contacts

GET /contacts/1

Content-Type: application/json

{
  "name": "Pavel",
  "phone": "+380677889910"
}

DELETE /contacts/1


