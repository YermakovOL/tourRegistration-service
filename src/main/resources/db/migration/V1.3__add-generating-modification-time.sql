CREATE OR REPLACE FUNCTION update_modification_time()
    RETURNS TRIGGER AS $$
BEGIN
    NEW.modification_time = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;
CREATE TRIGGER set_modification_time
    BEFORE INSERT OR UPDATE ON tour
    FOR EACH ROW
EXECUTE FUNCTION update_modification_time();